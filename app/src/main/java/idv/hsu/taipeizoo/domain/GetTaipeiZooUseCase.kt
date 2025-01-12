package idv.hsu.taipeizoo.domain

import idv.hsu.data.remote.source.TaipeiZooRemoteRepository
import javax.inject.Inject

// TODO 說明 operator fun invoke()

class GetTaipeiZooUseCase @Inject constructor(
    private val repository: TaipeiZooRemoteRepository
) {
    operator fun invoke() = repository.getAllData()
}