package idv.hsu.taipeizoo.data.source.remote

import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import idv.hsu.taipeizoo.domain.repository.TaipeiZooRemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaipeiZooRemoteRepositoryImpl @Inject constructor(
    private val dataSource: TaipeiZooRemoteDataSourceImpl,
):TaipeiZooRemoteRepository {
    override fun getAllData(): Flow<ApiResultWrapper<TaipeiZooResponse>> =
        dataSource.getDataFlow()
}