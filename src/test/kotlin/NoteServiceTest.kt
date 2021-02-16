import NoteService.Companion.commentList
import NoteService.Companion.noteList
import junit.framework.Assert.assertEquals
import org.junit.Test

class NoteServiceTest {

    @Test
    fun addNote() {
        noteList.clear()
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.add(note)
        assertEquals(note, service.getById(1))
    }

    @Test
    fun addCommit() {
        noteList.clear()
        commentList.clear()
        val serviceN = NoteService()
        val serviceC = CommentService()
        val note = Notte(title = "Title", text = "text")
        serviceN.add(note)
        val comment = Comment(noteId = 1, text = "comment")
        serviceC.add(comment)
        assertEquals(comment, serviceC.getById(id = 1))
    }

    @Test
    fun deleteNote() {
        noteList.clear()
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.add(note)
        service.delete(1)
        assertEquals(null, service.getById(1))
    }

    @Test
    fun deleteCommit() {
        noteList.clear()
        commentList.clear()
        val serviceN = NoteService()
        val serviceC = CommentService()
        val note = Notte(title = "Title", text = "text")
        serviceN.add(note)
        val comment = Comment(noteId = 1, text = "comment")
        serviceC.add(comment)
        serviceC.delete(1)
        assertEquals(null, serviceC.getById(1))
    }

    @Test
    fun editNote() {
        noteList.clear()
        val service = NoteService()
        val note = Notte(text = "text")
        service.add(note)
        val noteText = "text11"
        service.edit(Notte(id = 1, text = noteText))
        assertEquals(noteText, service.getById(1)?.text)

    }

    @Test
    fun editCommit() {
        noteList.clear()
        commentList.clear()
        val serviceN = NoteService()
        val serviceC = CommentService()
        val note = Notte(title = "Title", text = "text")
        serviceN.add(note)
        val comment = Comment(text = "comment", noteId = 1)
        serviceC.add(comment)
        val commentText = "text11"
        serviceC.edit(Comment(id = 1, text = commentText))
        assertEquals(commentText, serviceC.getById(1)?.text)
    }

    @Test
    fun restoreNote() {
        noteList.clear()
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.add(note)
        service.delete(1)
        assertEquals(null, service.getById(1))
        service.restore(1)
        assertEquals(note, service.getById(1))
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteThrow() {
        noteList.clear()
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.add(note)
        service.add(note)
        service.add(note)
        service.delete(2)
        assertEquals(note, service.getById(2))
    }


}

