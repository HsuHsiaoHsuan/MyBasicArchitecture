package idv.hsu.taipeizoo.domain.repository

import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import kotlinx.coroutines.flow.Flow

interface TaipeiZooRemoteRepository {
    fun getAllData(): Flow<ApiResultWrapper<TaipeiZooResponse>>
}