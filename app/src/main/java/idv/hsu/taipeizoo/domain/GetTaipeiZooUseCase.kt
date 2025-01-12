package idv.hsu.taipeizoo.domain

import idv.hsu.data.source.remote.TaipeiZooRemoteRepository
import javax.inject.Inject

class GetTaipeiZooUseCase @Inject constructor(
    private val repository: TaipeiZooRemoteRepository
) {
    operator fun invoke() = repository.getAllData()
}