package idv.hsu.taipeizoo.data

import idv.hsu.taipeizoo.data.ApiResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class TaipeiZooDataSourceTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var dataSource: TaipeiZooDataSource<String>

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        dataSource = mockk()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getDataFlow should emit ApiResultWrapper`() = runTest {
        val testFlow = flow {
            emit(ApiResultWrapper.Success("Test Data"))
        }
        every { dataSource.getDataFlow() } returns testFlow

        val result = dataSource.getDataFlow().toList()

        assertEquals(1, result.size)
        assertTrue(result[0] is idv.hsu.data.util.ApiResultWrapper.Success)
        assertEquals("Test Data", ApiResultWrapper.Success.data)
        verify { dataSource.getDataFlow() }
    }

    @Test
    fun `getData should return ApiResultWrapper`() = runTest {
        val testResult = ApiResultWrapper.Success("Test Data")
        coEvery { dataSource.getData() } returns testResult

        val result = dataSource.getData()

        assertTrue(result is idv.hsu.data.util.ApiResultWrapper.Success)
        assertEquals("Test Data", ApiResultWrapper.Success.data)
        coVerify { dataSource.getData() }
    }
}