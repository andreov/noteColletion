class WallService {

    val noteList = mutableListOf<Note>()
    private val commentList = mutableListOf<Comment>()

    fun addNote(note: Note): Note {
        val idLast: Int = if (noteList.isEmpty()) note.id else noteList.lastIndex + 1
        note.id = idLast + 1
        noteList.add(note)
        return noteList.last()
    }

    fun findNoteById(id: Int): Note? {
        for (note in noteList) {
            if (note.id == id) return note
        }
        return null
    }

    fun findCommitById(id: Int): Comment? {
        for (commit in commentList) {
            if (commit.id == id) return commit
        }
        return null
    }

    fun addComment(comment: Comment) {
        if (findNoteById(comment.noteID) !== null) {
            val idLastComment: Int = if (commentList.isEmpty()) comment.id else comment.id + 1
            comment.id = idLastComment + 1
            commentList.add(comment)
            filterCommit(comment)

        } else {
            val id: Int = comment.noteID
            throw PostNotFoundException(message = "no note with id $id")
        }
    }

    fun deleteNote(note: Note): Boolean {
        if (findNoteById(note.id) !== null) {
            return noteList.remove(findNoteById(note.id))
        } else {
            throw PostNotFoundException("no note with id")
        }
    }

    fun deleteComment(comment: Comment) {
        if (findNoteById(comment.noteID) !== null)
            if (findCommitById(comment.id) !== null) {
                commentList.remove(findCommitById(comment.id))
                filterCommit(comment)
            } else {
                println("Комментарий не найден")
                return
            }
        else {
            println("Запись не найдена")
            return
        }

    }

    fun filterCommit(comment: Comment){
        val comFilter = commentList.filter {
            it.noteID == comment.noteID
        }.toMutableList()
        val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
        //noteList[indexNote] = Note(id = comment.noteID, comments = comFilter)
        noteList[indexNote].id=comment.noteID
        noteList[indexNote].comments=comFilter
    }
}




