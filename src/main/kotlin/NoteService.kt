class NoteService : CrudService<Notte> {

    companion object {
        val noteList = mutableListOf<Notte>()
        val commentList = mutableListOf<Comment>()
    }

    override fun add(entity: Notte): Notte {
        val idLast: Int = if (noteList.isEmpty()) entity.id else noteList.lastIndex + 1
        entity.id = idLast + 1
        noteList.add(entity)
        return noteList.last()
    }


    override fun getById(id: Int): Notte? {
        for (note in noteList) {
            if (note.id == id && !note.isDeleted) return note
        }
        return null
    }

    override fun delete(id: Int) {
        for (note in noteList) {
            if (note.id == id) {
                note.isDeleted = true
                println("Заметка id=$id удалена")
                return
            }
        }
        throw NoteNotFoundException("Запись не найдена")
    }

    override fun read(): List<Notte> {
        return noteList.filter {
            !it.isDeleted
        }
    }

    override fun edit(entity: Notte) {
        getById(entity.id)?.let {
            noteList[it.id - 1] = it.copy(title = entity.title, text = entity.text)
            return
        }
            throw NoteNotFoundException("Запись не найдена")


    }

     override fun restore(id: Int) {
         for (note in noteList) {
             if (note.id == id) {
                 note.isDeleted = false
                 println("Заметка id=$id restore")
                 return
             }
         }
         throw NoteNotFoundException("Запись id=$id не найдена")
    }
}





