package idv.hsu.taipeizoo.data.source

import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import kotlinx.coroutines.flow.Flow

abstract class TaipeiZooRemoteDataSource {
    abstract fun getDataFlow(): Flow<ApiResultWrapper<TaipeiZooResponse>>
}