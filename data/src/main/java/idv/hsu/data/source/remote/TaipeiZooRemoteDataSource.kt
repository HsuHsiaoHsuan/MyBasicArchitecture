package idv.hsu.data.source.remote

import idv.hsu.data.model.TaipeiZooResponse
import idv.hsu.data.ApiResultWrapper
import idv.hsu.data.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteDataSource @Inject constructor(
    private val api: TaipeiZooApi
) {
    fun getDataFlow(): Flow<ApiResultWrapper<TaipeiZooResponse>> = safeApiCall {
        api.getAllData()
    }
}