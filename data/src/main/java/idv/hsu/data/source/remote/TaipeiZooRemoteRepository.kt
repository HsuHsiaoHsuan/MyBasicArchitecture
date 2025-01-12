package idv.hsu.data.source.remote

import idv.hsu.data.model.TaipeiZooResponse
import idv.hsu.data.ApiResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteRepository @Inject constructor(
    private val dataSource: TaipeiZooRemoteDataSource,
) {
    fun getAllData(): Flow<ApiResultWrapper<TaipeiZooResponse>> =
        dataSource.getDataFlow()
}