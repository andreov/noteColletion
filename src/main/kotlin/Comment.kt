data class Comment(
    var id: Int = 0,
    val noteID:Int,
    val ownerId: Int = 1,
    val text: String = "" ,
    var isDeleted: Boolean = false,
    val date: Long = 0L,
    //val comments: MutableList<Comment> = mutableListOf(),
    )
