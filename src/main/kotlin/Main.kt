fun main(){

    val wallService = WallService()
    println("добавление заметки без комментариев")
    wallService.addNote(Note(title = "Заголовок1", text = "первая запись"))
    wallService.addNote(Note(title = "Заголовок2", text = "вторая запись"))
    wallService.addNote(Note(title = "Заголовок3", text = "3 запись"))
    println(wallService.noteList)
    println()

    println("добавление комментариев к заметкам")
    wallService.addComment(Comment(noteID = 2, text = "комментарий к заметке2"))
    wallService.addComment(Comment(noteID = 3, text = "комментарий к заметке3"))
    println(wallService.noteList)
    println(wallService.findNoteById(3))
    println()

    println("удаление заметки")
    wallService.deleteNote(Note(id=1))
    println(wallService.noteList)
    println()

    println("удаление Комментария")
    wallService.deleteComment(Comment(id=1, noteID = 2))
    wallService.deleteComment(Comment(id=1, noteID = 1))
    println(wallService.findNoteById(2))
    println()

    println("редактирование заметки")
    wallService.editNote(Note(id=2, title = "htlfrnbhjdfybt gthdjq pfvtnrb"))
    println(wallService.findNoteById(2))
    println()



    //println(wallService.noteList)
    //println(wallService.findNoteById(2))




}