package idv.hsu.data.source.remote

import com.squareup.moshi.Moshi
import idv.hsu.data.ApiResultWrapper
import idv.hsu.data.model.Animal
import idv.hsu.data.model.Result
import idv.hsu.data.model.TaipeiZooResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets


class TaipeiZooRemoteRepositoryTest {
    private lateinit var mockkDataSource: TaipeiZooRemoteDataSource
    private lateinit var repository: TaipeiZooRemoteRepository

    @BeforeEach
    fun setUp() {
        mockkDataSource = mockk()
        repository = TaipeiZooRemoteRepository(mockkDataSource)
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getAllData emits correct flow from data source`() = runTest {
        val mockAnimal = loadMockAnimal()
        val mockResult = Result(
            limit = 1,
            offset = 0,
            count = 1,
            sort = "",
            results = listOf(mockAnimal)
        )
        val mockResponse = TaipeiZooResponse(result = mockResult)
        val expected = ApiResultWrapper.Success(mockResponse)
        val flow = flowOf(expected)

        coEvery { mockkDataSource.getDataFlow() } returns flow

        val result = repository.getAllData().toList()

        assertEquals(listOf(expected), result)
        coVerify(exactly = 1) { mockkDataSource.getDataFlow() }
    }

    @Test
    fun `getAllData emits error when data source returns error()`() = runTest {
        val expected = ApiResultWrapper.Error(error = "API Error")
        val flow = flowOf(expected)

        coEvery { mockkDataSource.getDataFlow() } returns flow

        val result = repository.getAllData().toList()

        assertEquals(listOf(expected), result)
        coVerify(exactly = 1) { mockkDataSource.getDataFlow() }
    }

    private fun loadMockAnimal(): Animal {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("mock_animal.json")
        val json = inputStream!!.readBytes().toString(StandardCharsets.UTF_8)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Animal::class.java)
        return adapter.fromJson(json)!!
    }
}