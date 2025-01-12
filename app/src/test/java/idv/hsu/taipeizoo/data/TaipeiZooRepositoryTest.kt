package idv.hsu.taipeizoo.data

import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class TaipeiZooRepositoryTest {

    val testDispatcher = StandardTestDispatcher()
    private val mockDataSource: TaipeiZooDataSource<TaipeiZooResponse> = mockk()
    private val repository: TaipeiZooRepository = object : TaipeiZooRepository {
        override fun getAllDataFlow(): Flow<ApiResultWrapper<TaipeiZooResponse>> {
            return mockDataSource.getDataFlow()
        }

        override suspend fun getAllData(): ApiResultWrapper<TaipeiZooResponse> {
            return mockDataSource.getData()
        }
    }

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getAllData should return data`() = runTest {
        val mockApiResult = ApiResultWrapper.Success(mockResponse)
        coEvery { mockDataSource.getData() } returns mockApiResult

        val result = repository.getAllData()

        assertEquals(mockApiResult, result)

        coVerify { mockDataSource.getData() }
    }

    @Test
    fun `test getAllDataFlow should emit data`() = runTest {
        val mockApiResultFlow = flowOf(ApiResultWrapper.Success(mockResponse))
        coEvery { mockDataSource.getDataFlow() } returns mockApiResultFlow

        val resultFlow = repository.getAllDataFlow()

        resultFlow.collect { result ->
            assertEquals(ApiResultWrapper.Success(mockResponse), result)
        }

        coVerify { mockDataSource.getDataFlow() }
    }
}