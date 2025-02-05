import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class AutoTest {
    @Before
    fun clearBeforeTest() {
        WallSevice.clear()
    }

    @Test
    fun addPostTest() {
        val post = WallSevice.add(
            Post(
                id = 1,
                text = "Hello World",
                fromId = 12,
                date = 132,
                likes = Likes(count = 1, userLike = false),
                views = 1
            )
        )
        assertEquals(1, post.id)
    }

    @Test
    fun updateTrue() {
        WallSevice.add(
            Post(
                id = 2,
                text = "Hello World",
                fromId = 2,
                date = 1234,
                likes = Likes(count = 1, userLike = true),
                views = 12
            )
        )

        val updateResult = WallSevice.update(
            Post(
                id = 1,
                text = "Hello World",
                fromId = 2,
                date = 1234,
                likes = Likes(count = 1, userLike = true),
                views = 12
            )
        )

        assertTrue(updateResult)
    }

    @Test
    fun updateNoTrue() {

        val updateResult = WallSevice.update(
            Post(
                id = 3,
                text = "Hello World",
                fromId = 2,
                date = 1234,
                likes = Likes(count = 1, userLike = true),
                views = 12
            )
        )
        assertFalse(updateResult)


    }


}

