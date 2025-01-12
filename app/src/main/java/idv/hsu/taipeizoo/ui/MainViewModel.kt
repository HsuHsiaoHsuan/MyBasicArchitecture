package idv.hsu.taipeizoo.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import idv.hsu.taipeizoo.data.ApiResultWrapper
import idv.hsu.taipeizoo.data.dto.Animal
import idv.hsu.taipeizoo.domain.usecase.GetTaipeiZooUseCase
import idv.hsu.taipeizoo.util.MVIViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTaipeiZooUseCase: GetTaipeiZooUseCase
) : MVIViewModel<MainUiIntent, MainUiState>(
    initialUi = MainUiState.Idle
) {
    private var fetchJob: Job? = null

    override suspend fun handleIntent(intent: MainUiIntent) {
        when (intent) {
            MainUiIntent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            setUiState(MainUiState.Loading)
            getTaipeiZooUseCase.invoke().collectLatest { result ->
                setUiState(
                    when (result) {
                        is ApiResultWrapper.Success -> MainUiState.ShowZooAnimals(result.data.result.results)
                        is ApiResultWrapper.Error -> MainUiState.Error(result.error)
                        ApiResultWrapper.NetworkError -> MainUiState.Error("Network Error")
                    }
                )
            }
        }
    }
}

sealed class MainUiIntent {
    data object FetchData : MainUiIntent()
}

sealed class MainUiState {
    data object Idle : MainUiState()
    data object Loading : MainUiState()
    data class ShowZooAnimals(val animals: List<Animal>) : MainUiState()
    data class Error(val message: String?) : MainUiState()
}