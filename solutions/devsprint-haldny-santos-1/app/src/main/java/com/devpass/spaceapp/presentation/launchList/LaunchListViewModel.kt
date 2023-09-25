package com.devpass.spaceapp.presentation.launchList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.mapper.toLaunchPresentation
import com.devpass.spaceapp.domain.usecase.FetchLaunchesUseCase
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LaunchListViewModel(private val fetchLaunchesUseCase: FetchLaunchesUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<LaunchListUiState> = MutableStateFlow(
        LaunchListUiState.Success(
            emptyList()
        )
    )
    val uiState: StateFlow<LaunchListUiState> = _uiState

    fun getLaunches() {
        viewModelScope.launch {
            fetchLaunchesUseCase()
                .onStart {
                    _uiState.value = LaunchListUiState.Loading
                }
                .collect { resultData ->
                    when (resultData) {
                        is ResultData.Success -> {
                            _uiState.value = LaunchListUiState.Success(
                                resultData.data.docs.map {
                                    it.toLaunchPresentation(it)
                                }
                            )
                        }

                        is ResultData.Error -> _uiState.value =
                            LaunchListUiState.Error(resultData.throwable)
                    }
                }
        }
    }

    companion object {
        fun providerFactory(fetchLaunchesUseCase: FetchLaunchesUseCase): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LaunchListViewModel(fetchLaunchesUseCase = fetchLaunchesUseCase) as T
                }
            }
    }
}

sealed interface LaunchListUiState {
    object Loading : LaunchListUiState
    data class Success(val data: List<LaunchModel>) : LaunchListUiState
    data class Error(val throwable: Throwable) : LaunchListUiState
}
