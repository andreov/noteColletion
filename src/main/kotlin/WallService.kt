class WallService {

    val noteList = mutableListOf<Note>()


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
        println("Запись не найдена")
        return null
    }

    fun editNote(note: Note):Note? {
        if (findNoteById(note.id) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(note.id))
            noteList[indexNote] = note
            return noteList[indexNote]
        } else {
            println("Запись не найдена")
            return null
        }
    }

    fun editComment(comment: Comment): Comment? {
        if (findNoteById(comment.noteID) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
            val note: Note = noteList[indexNote]
            if (findCommitById(comment.id, note) !== null) {
                val indexCommit: Int = note.comments.indexOf(findCommitById(comment.id, note))
                note.comments[indexCommit] = comment
                return comment
            } else {
                println("Комментарий ${comment.id} не найден")
                return null
            }
        } else {
            println("Запись ${comment.noteID} не найдена")
            return null
        }
    }

    fun findCommitById(id: Int, note: Note): Comment? {
        for (commit in note.comments) {
            if (commit.id == id) return commit
        }
        println("Комментарий $id не найден")
        return null
    }

    fun addComment(comment: Comment) {
        if (findNoteById(comment.noteID) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
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
            println("Запись не найдена")
            return false
        }
    }

    fun deleteComment(comment: Comment): Boolean {
        if (findNoteById(comment.noteID) !== null) {
            val indexNote: Int = noteList.indexOf(findNoteById(comment.noteID))
            val note: Note = noteList[indexNote]
            if (findCommitById(comment.id, note) !== null) {
                note.comments.remove(findCommitById(comment.id, note))
                println("Комментарий ${comment.id} удален")
                return true
            } else {
                println("Комментарий ${comment.id} не найден")
                return false
            }
        } else {
            println("Запись ${comment.noteID} не найдена")
            return false
        }
    }
}




