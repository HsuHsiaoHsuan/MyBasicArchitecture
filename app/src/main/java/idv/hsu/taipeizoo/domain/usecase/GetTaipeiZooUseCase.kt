package idv.hsu.taipeizoo.domain.usecase

import idv.hsu.taipeizoo.data.source.remote.TaipeiZooRemoteRepositoryImpl
import javax.inject.Inject

class GetTaipeiZooUseCase @Inject constructor(
    private val repository: TaipeiZooRemoteRepositoryImpl
) {
    operator fun invoke() = repository.getAllData()
}