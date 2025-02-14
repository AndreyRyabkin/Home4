interface Attachment {
    val type: String
}

class videoAttachment(
    val video: Video,
) : Attachment {
    override val type: String = "Video"
}

class Video(
    val id: Int,
    val ownerId: Int,
    val titlr: String,
    val duration: Int,
)

class photoAttachment(
    val photo: Photo,
) : Attachment {
    override val type: String = "Photo"
}

class Photo(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
)

class graafittiAttachment(
    val graffiti: Graffiti,
) : Attachment {
    override val type: String = "Grafitti"
}

class Graffiti(
    val id: Int,
    val ownerId: Int,
    val photo130: String,
    val photo604: String,
)

class audioAttachment(
    val audio: Audio,
) : Attachment {
    override val type: String = "Audio"
}

class Audio(
    val id: String,
    val date: String,
    val artist: String,
    val albom: String,
)

class urlAttachments(
    val url: Url,
) : Attachment {
    override val type: String = "Url"
}

class Url(
    val title: String,
    val caption: String,
    val description: String,
    val previewpage: String,
)

data class Post(
    val id: Int,
    val text: String = " ",
    val fromId: Int? = null,
    val date: Int? = null,
    val likes: Likes = Likes(),
    val views: Int = 0,
    val attachments: Array<Attachment> = emptyArray(),
)


data class Likes(
    val count: Int = 0,
    val userLike: Boolean = true,
)

data class Comment(
    val id: Int,
    val postId: Int,
    val date: Int,
    val text: String,
)

class PostNotFoundException(message: String) : Exception(message)

object WallSevice {
    private var comments = emptyArray<Comment>()
    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (post.id == postId) {
                comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Пост с ID $postId не найден")
    }


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
            id = 1,
            text = "Hello World",
            fromId = null,
            date = 12 / 12 / 12,
            likes = Likes(count = 1, userLike = true),
            views = 12,

            )
    )
    WallSevice.add(
        Post(
            text = "Holla Mundo",
            id = 1,
            fromId = 2,
            date = null,
            likes = Likes(count = 100, userLike = true),
            views = 12
        )
    )


    WallSevice.printPost()
    println(
        WallSevice.update(
            Post(
                text = "Holla Mundo",
                id = 2,
                fromId = 2,
                date = 4324,
                likes = Likes(count = 100, userLike = true),
                views = 12

            )
        )
    )
    try {

        val addComment = WallSevice.createComment(
            postId = 1,
            comment = Comment(id = 1, date = 140224, text = "Новый пост", postId = 1)
        )
        println("Комментарий добавлен: $addComment")
    } catch (e: PostNotFoundException) {
        println(e.message)
    }
    try {

        val addComment = WallSevice.createComment(
            postId = 3,
            comment = Comment(id = 3, date = 140224, text = "Новый пост", postId = 3)
        )
        println("Комментарий добавлен: $addComment")
    } catch (e: PostNotFoundException) {
        println(e.message)
    }

}






