package idv.hsu.data.remote.source

import idv.hsu.data.module.TaipeiZooResponse
import idv.hsu.data.util.ApiResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteRepository @Inject constructor(
    private val dataSource: TaipeiZooRemoteDataSource,
) {
    fun getAllData(): Flow<ApiResultWrapper<TaipeiZooResponse>> =
        dataSource.getDataFlow()
}