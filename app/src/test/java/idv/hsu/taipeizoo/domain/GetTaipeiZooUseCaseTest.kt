package idv.hsu.taipeizoo.domain

import idv.hsu.taipeizoo.data.TaipeiZooRepository
import idv.hsu.taipeizoo.data.mockResponse2
import idv.hsu.taipeizoo.domain.usecase.GetTaipeiZooUseCase
import idv.hsu.taipeizoo.util.ApiResultWrapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class GetTaipeiZooUseCaseTest {

    private lateinit var repository: TaipeiZooRepository
    private lateinit var useCase: GetTaipeiZooUseCase

    @BeforeEach
    fun setup() {
        repository = mockk()
        useCase = GetTaipeiZooUseCase(repository)
    }

    @Test
    fun `invoke should return data from repository`() = runTest {
        val mockResponse = ApiResultWrapper.Success(mockResponse2)
        coEvery { repository.getAllData() } returns mockResponse

        val result = useCase()

        assertEquals(mockResponse, result)
        coVerify(exactly = 1) { repository.getAllData() }
    }

    @Test
    fun `invoke should handle error correctly`() = runTest {
        val mockError = ApiResultWrapper.Error(error = "Network error")
        coEvery { repository.getAllData() } returns mockError

        val result = useCase()

        assertEquals(mockError, result)
        coVerify(exactly = 1) { repository.getAllData() }
    }
}