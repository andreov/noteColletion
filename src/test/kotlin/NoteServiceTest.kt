import junit.framework.Assert.assertEquals
import org.junit.Test

class NoteServiceTest {

    @Test
    fun addNote() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        assertEquals(note, service.findNoteById(1))
    }

    @Test
    fun addCommit() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        val comment = Comment(text = "comment", noteId = 1)
        service.addComment(comment)
        assertEquals(comment, service.findCommitById(id = 1, note = note))
    }

    @Test
    fun deleteNote() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        assertEquals(true, service.deleteNote(note))
        assertEquals(false, service.deleteNote(Notte(id=2)))
    }


    @Test
    fun deleteCommit() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        val comment = Comment(text = "comment", noteId = 1)
        service.addComment(comment)
        assertEquals(true, service.deleteComment(comment))
        assertEquals(false, service.deleteComment(Comment(id=2)))
        assertEquals(false, service.deleteComment(Comment(noteId= 2)))
    }

    @Test
    fun editNote() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        assertEquals(note, service.editNote(note))
        assertEquals(null, service.editNote(Notte(id = 2)))
    }

    @Test
    fun editCommit() {
        val service = NoteService()
        val note = Notte(title = "Title", text = "text")
        service.addNote(note)
        val comment = Comment(text = "comment", noteId = 1)
        service.addComment(comment)
        assertEquals(comment, service.editComment(comment))
        assertEquals(null, service.editComment(Comment(id=2)))
        assertEquals(null, service.editComment(Comment(noteId= 2)))
    }

}

