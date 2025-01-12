package idv.hsu.taipeizoo.data.source.remote

import idv.hsu.taipeizoo.data.TaipeiZooDataSource
import idv.hsu.taipeizoo.data.mockResponse
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import idv.hsu.taipeizoo.util.ApiResultWrapper
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TaipeiZooRemoteRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mockDataSource: TaipeiZooDataSource<TaipeiZooResponse>
    private lateinit var repository: TaipeiZooRemoteRepositoryImpl

    @BeforeEach
    fun setUp() {
        mockDataSource = mockk()
        repository = TaipeiZooRemoteRepositoryImpl(
            dataSource = mockDataSource,
            dispatcher = testDispatcher
        )
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test getAllDataFlow emits correct data`() = runTest(testDispatcher) {
        val expectedResponse = ApiResultWrapper.Success(mockResponse)
        val flowData = flowOf(expectedResponse)

        coEvery { mockDataSource.getDataFlow() } returns flowData

        val result = repository.getAllDataFlow().toList()

        assertEquals(listOf(expectedResponse), result)
        coVerify(exactly = 1) { mockDataSource.getDataFlow() }
    }

    @Test
    fun `test getAllData returns correct data`() = runTest(testDispatcher) {
        val expectedResponse = ApiResultWrapper.Success(mockResponse)

        coEvery { mockDataSource.getData() } returns expectedResponse

        val result = repository.getAllData()

        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { mockDataSource.getData() }
    }

    @Test
    fun `test getAllData handles error`() = runTest(testDispatcher) {
        val expectedError = ApiResultWrapper.Error(error = "Network Error")

        coEvery { mockDataSource.getData() } returns expectedError

        val result = repository.getAllData()

        assertEquals(expectedError, result)
        coVerify(exactly = 1) { mockDataSource.getData() }
    }
}