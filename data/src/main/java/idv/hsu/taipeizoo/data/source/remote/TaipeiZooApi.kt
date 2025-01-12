package idv.hsu.taipeizoo.data.source.remote

import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaipeiZooApi {
    /*
     * https://data.taipei/api/v1/dataset/6afa114d-38a2-4e3c-9cfd-29d3bd26b65b?scope=resourceAquire
     */
    @GET("api/v1/dataset/6afa114d-38a2-4e3c-9cfd-29d3bd26b65b")
    suspend fun getAllData(
        @Query("scope") scope: String = "resourceAquire"
    ): Response<TaipeiZooResponse>
}
