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

    fun editNote(note: Note){
        if (findNoteById(note.id) !== null){
            val indexNote: Int = noteList.indexOf(findNoteById(note.id))
            noteList[indexNote] = note

        }else{
            println("Запись не найдена")
            return
        }
    }

    fun editComment(comment: Comment){

    }

    fun findCommitById(id: Int,note: Note): Comment? {
        for (commit in note.comments) {
            if (commit.id == id) return commit
        }
        return null
    }

    fun addComment(comment: Comment) {
        if (findNoteById(comment.noteID) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
            //noteList[indexNote]=note
            val idLastComment: Int = if (noteList[indexNote].comments.isEmpty()) comment.id else comment.id + 1
            comment.id = idLastComment + 1
            noteList[indexNote].comments.add(comment)

        } else {
            println("Запись не найдена")
            return
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
        if (findNoteById(comment.noteID) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
            val note: Note = noteList[indexNote]
            if (findCommitById(comment.id, note) !== null) {
                note.comments.remove(findCommitById(comment.id, note))
                println("Комментарий ${comment.id} удален")
            } else {
                println("Комментарий ${comment.id} не найден")
                return
            }
        }else {
            println("Запись ${comment.noteID} не найдена")
            return
        }

    }

    private fun filterCommit(comment: Comment){
        val comFilter = commentList.filter {
            it.noteID == comment.noteID
        }.toMutableList()
        val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
        //noteList[indexNote] = Note(id = comment.noteID, comments = comFilter)
        noteList[indexNote].id=comment.noteID
        noteList[indexNote].comments=comFilter
    }
}




