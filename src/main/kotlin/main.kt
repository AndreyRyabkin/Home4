data class Post(
    val id: Int,
    val text: String = " ",

    val fromId: Int,
    val date: Int,
    val likes: Likes = Likes(),

    val views: Int = 0,
)


data class Likes(
    val count: Int = 0,
    val userLike: Boolean = true
)


object WallSevice {


    private var posts = emptyArray<Post>()
    private var lastID = 0
    fun add(post: Post): Post {
        posts += post.copy(++lastID)

        return posts.last()

    }

    fun printPost() {
        for (post in posts) {
            print(post)
            print(" ")
        }
        println()
    }

    fun update(new: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (new.id == post.id) {
                posts[index] = new.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastID = 0
    }
}


fun main() {
    WallSevice.add(
        Post(
            text = "Hello World",
            id = 0,
            fromId = 2,
            date = 1234,
            likes = Likes(count = 1, userLike = true),
            views = 12
        )
    )
    WallSevice.add(
        Post(
            text = "Holla Mundo",
            id = 1,
            fromId = 2,
            date = 4324,
            likes = Likes(count = 100, userLike = true),
            views = 12
        )
    )

    WallSevice.printPost()
    println(
        WallSevice.update(
            Post(
                text = "Holla Mundo",
                id = 5,
                fromId = 2,
                date = 4324,
                likes = Likes(count = 100, userLike = true),
                views = 12

            )
        )
    )
}





