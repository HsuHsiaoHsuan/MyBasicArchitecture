package idv.hsu.data

import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.safeApiCall
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import retrofit2.Response

class SafeApiCallTest {

    @Test
    fun `safeApiCall emits Success when response is successful`() = runTest {
        val expected = "data"
        val response = Response.success(expected)

        val flow = safeApiCall { response }
        val result = flow.first()

        assertEquals(ApiResultWrapper.Success(expected), result)
    }

    @Test
    fun `safeApiCall emits Error when response is unsuccessful`() = runTest {
        val errorBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "error message")
        val response = Response.error<String>(404, errorBody)

        val flow = safeApiCall { response }
        val result = flow.first()

        assert(result is ApiResultWrapper.Error)
        val error = result as ApiResultWrapper.Error
        assertEquals(404, error.code)
        assertEquals("error message", error.error)
    }

    @Test
    fun `safeApiCall emits NetworkError on IOException`() = runTest {
        val apiCall: suspend () -> Response<String> = { throw java.io.IOException("Network error") }

        val flow = safeApiCall(apiCall)
        val result = flow.first()

        assertEquals(ApiResultWrapper.NetworkError, result)
    }
}