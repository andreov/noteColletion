data class Comment(
    val id: Int = 1,
    val ownerId: Int = 1,
    var text: String,
    var isDeleted: Boolean = false,
    val date: Long = 0L,
    //val comments: MutableList<Comment> = mutableListOf(),

)
