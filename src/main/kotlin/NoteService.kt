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
        if (getById(entity.id) !== null) {
            noteList[entity.id - 1] = entity
        } else {
            throw NoteNotFoundException("Запись не найдена")

        }
    }


}





