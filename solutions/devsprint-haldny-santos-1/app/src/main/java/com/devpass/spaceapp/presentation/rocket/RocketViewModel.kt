package com.devpass.spaceapp.presentation.rocket

import androidx.lifecycle.ViewModel
import com.devpass.spaceapp.data.ResultData
import com.devpass.spaceapp.domain.mapper.toRocketPresentation
import com.devpass.spaceapp.domain.usecase.RocketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketViewModel @Inject constructor(
    private val rocketUseCase: RocketUseCase
): ViewModel() {

    private val _uiState: MutableStateFlow<RocketUiState> = MutableStateFlow(
        RocketUiState.Loading
    )
    val uiState: StateFlow<RocketUiState> = _uiState

    fun getRocket(id: String) {
        GlobalScope.launch {
            rocketUseCase(id)
                .onStart {
                    _uiState.value = RocketUiState.Loading
                }
                .collect { resultData ->
                    when (resultData) {
                        is ResultData.Success -> {
                            _uiState.value = RocketUiState.Success(
                                resultData.data.toRocketPresentation()
                            )
                        }

                        is ResultData.Error -> _uiState.value =
                            RocketUiState.Error(resultData.throwable)
                    }
                }
        }
    }
}

sealed interface RocketUiState {
    object Loading : RocketUiState
    data class Success(val data: RocketModel) : RocketUiState
    data class Error(val throwable: Throwable) : RocketUiState
}
