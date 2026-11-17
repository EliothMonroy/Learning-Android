package xyz.eliothmonroy.placeholdersocial

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PlaceholderUnitTest {

    @Test
    fun viewModelError()= runTest {
        val viewModel = PlaceholderViewModel(FakeCrashingRepository(), dispatcher = StandardTestDispatcher(testScheduler))
        advanceUntilIdle()
        assertTrue(viewModel.uiState.value is UiState.Error)
    }

}


class FakeCrashingRepository : PlaceholderRepository {
    override suspend fun getUsers(): List<UserModel> {
        throw IOException()
    }

    override suspend fun getPosts(): List<PostModel> {
        throw Exception()
    }

    override suspend fun getComments(): List<CommentModel> {
        throw Exception()
    }

}