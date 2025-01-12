package idv.hsu.data.remote.source

import idv.hsu.data.module.TaipeiZooResponse
import idv.hsu.data.util.ApiResultWrapper
import idv.hsu.data.util.safeApiCallFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteDataSource @Inject constructor(
    private val api: TaipeiZooApi
) {
    fun getDataFlow(): Flow<ApiResultWrapper<TaipeiZooResponse>> = safeApiCallFlow {
        api.getAllData()
    }
}