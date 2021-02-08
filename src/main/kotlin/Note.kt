 data class Note (
     var id: Int = 0,
     var title: String? = null,
     val ownerId: Int = 1,
     var text: String = "",
     //var isDeleted: Boolean = false,
     val date: Long = 0L,
     var comments: MutableList<Comment> = mutableListOf()
 )

