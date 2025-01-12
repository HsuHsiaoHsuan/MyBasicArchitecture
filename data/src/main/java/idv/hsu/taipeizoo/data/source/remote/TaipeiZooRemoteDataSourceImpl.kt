package idv.hsu.taipeizoo.data.source.remote

import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import idv.hsu.taipeizoo.data.safeApiCall
import idv.hsu.taipeizoo.data.source.TaipeiZooRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteDataSourceImpl @Inject constructor(
    private val api: TaipeiZooApi
): TaipeiZooRemoteDataSource() {
    override fun getDataFlow(): Flow<ApiResultWrapper<TaipeiZooResponse>> = safeApiCall {
        api.getAllData()
    }
}