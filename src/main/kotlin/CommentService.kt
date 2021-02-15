import NoteService.Companion.commentList
import NoteService.Companion.noteList

class CommentService:CrudService<Comment> {



    override fun add(entity: Comment):Comment {
        val idLast: Int = if (commentList.isEmpty()) entity.id else commentList.lastIndex + 1
        entity.id = idLast + 1
        commentList.add(entity)
        noteList[entity.noteId-1].comments.add(commentList.last())
        return commentList.last()
    }

    override fun getById(id: Int): Comment? {
        for (commit in commentList) {
            if (commit.id == id && !commit.isDeleted) return commit
        }
        return null

    }

    override fun delete(id: Int) {
        if (getById(id) !== null) {
            commentList[id-1].isDeleted = true
        } else {
            println("Запись не найдена")
            return
        }
    }

    override fun read(): List<Comment> {
        return commentList.filter {
            !it.isDeleted
        }
    }

    override fun edit(entity: Comment) {
        if (getById(entity.id) !== null) {
            commentList[entity.id-1] = entity
        } else {
            throw NoteNotFoundException("Запись не найдена")

        }
    }

//    fun addCommentTo(id: Int) {
//        if (getById(id) !== null) {
//            val indexNote: Int = noteList.indexOf(getById(comment.noteId))
//            val idLastComment: Int = if (noteList[indexNote].comments.isEmpty()) comment.id else comment.id + 1
//            comment.id = idLastComment + 1
//            noteList[indexNote].comments.add(comment)
//
//        } else {
//            println("Запись не найдена")
//            return
//        }
}