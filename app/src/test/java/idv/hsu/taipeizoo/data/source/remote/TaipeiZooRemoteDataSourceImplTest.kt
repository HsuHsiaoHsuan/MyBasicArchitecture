package idv.hsu.taipeizoo.data.source.remote

import idv.hsu.taipeizoo.data.mockResponse2
import idv.hsu.taipeizoo.data.ApiResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class TaipeiZooRemoteDataSourceImplTest {

    private lateinit var dataSource: TaipeiZooRemoteDataSourceImpl
    private val api: TaipeiZooApi = mockk()

    @BeforeEach
    fun setup() {
        dataSource = TaipeiZooRemoteDataSourceImpl(api)
    }

    @Test
    fun `getDataFlow should return success when api returns data`() = runTest {
        val mockResponse = mockResponse2
        coEvery { api.getAllData() } returns mockResponse

        val results = dataSource.getDataFlow()
            .catch { emit(ApiResultWrapper.Error(ApiResultWrapper.Error.error = "Flow Exception")) }
            .toList()

        assertTrue(results.first() is ApiResultWrapper.Success)
        assertEquals(mockResponse, (results.first() as ApiResultWrapper.Success).data)
    }

    @Test
    fun `getDataFlow should return error when api throws exception`() = runTest {
        coEvery { api.getAllData() } throws RuntimeException("Network Error")

        val result = dataSource.getDataFlow().first()

        assertTrue(result is ApiResultWrapper.Error)
        assertEquals("Network Error", (result as ApiResultWrapper.Error).error)
    }

    @Test
    fun `getData should return success when api returns data`() = runTest {
        val mockResponse = mockResponse2
        coEvery { api.getAllData() } returns mockResponse

        val result = dataSource.getData()

        assertTrue(result is ApiResultWrapper.Success)
        assertEquals(mockResponse, (result as ApiResultWrapper.Success).data)
    }

    @Test
    fun `getData should return error when api throws exception`() = runTest {
        coEvery { api.getAllData() } throws RuntimeException("API Error")

        val result = dataSource.getData()

        assertTrue(result is ApiResultWrapper.Error)
        assertEquals("API Error", (result as ApiResultWrapper.Error).error)
    }

    @Test
    fun `verify api call count`() = runTest {
        coEvery { api.getAllData() } returns mockResponse2

        dataSource.getData()
        coVerify(exactly = 1) { api.getAllData() }
    }
}