import NoteService.Companion.commentList
import NoteService.Companion.noteList
import kotlin.math.E

fun main(){

    val noteService = NoteService()
    val commentService=CommentService()

    println("добавление заметки без комментариев")
    noteService.add(Notte(title = "Заголовок1", text = "первая запись"))
    noteService.add(Notte(title = "Заголовок2", text = "вторая запись"))
    noteService.add(Notte(title = "Заголовок3", text = "3 запись"))
    println(noteList)
    println()

    println("добавление комментариев к заметкам")
    commentService.add(Comment(noteId = 2, text = "1 комментарий к заметке2"))
    commentService.add(Comment(noteId = 2, text = "2 комментарий к заметке2"))
    commentService.add(Comment(noteId = 3, text = "1 комментарий к заметке3"))
    println(noteList)
    println(commentList)
    println()

    println("удаление заметки")
    noteService.delete(id=1)

    println()

    println("Вывод всех заметок")
    println(noteService.read())
    println()

    println("удаление Комментария")
    commentService.delete(id=1)
    println(commentService.read())
    println(noteService.read())
    println()

    println("редактирование заметки")
    println(noteService.getById(2))
    noteService.edit(Notte(id = 2, title = "222222222", text = "222222222"))
    println(noteService.getById(2))
    println()

    println("редактирование комментария")
    println(commentService.getById(2))
    commentService.edit(Comment(id = 2, text = "33333333333333333333"))
    println(commentService.getById(2))
    println(noteService.read())
    println()

    println("выосстановление заметки")
    println(noteService.read())
    noteService.restore(id=1)
    println(noteService.read())
    println()

    println("выосстановление комментария")
    println(commentService.read())
    println(noteService.read())
    commentService.restore(id=1)
    println(commentService.read())
    println(noteService.read())
    println()








}