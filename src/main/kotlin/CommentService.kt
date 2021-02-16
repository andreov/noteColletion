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
        getById(id)?.let {
            it.isDeleted = true
            noteList[it.noteId-1].comments.remove(it)
            return
        }
        println("Запись не найдена")
    }


    override fun read(): List<Comment> {
        return commentList.filter {
            !it.isDeleted
        }
    }

    override fun edit(entity: Comment) {
        getById(entity.id)?.let {
            commentList[it.id-1] = it.copy(text = entity.text)
            val index = noteList[it.noteId-1].comments.indexOf(it)
           // println(index)
            noteList[it.noteId-1].comments[index] = commentList[it.id-1]
            return
        }
            throw NoteNotFoundException("Комментарий не найден")

    }

    override fun restore(id: Int) {
        for (commit in commentList) {
            if (commit.id == id) {
                commit.isDeleted = false
                println("Комментарий id=$id restore")
                noteList[commit.noteId-1].comments.add(commit)
                return
            }
        }
        throw NoteNotFoundException("Комментарий id=$id не найден")
    }


}