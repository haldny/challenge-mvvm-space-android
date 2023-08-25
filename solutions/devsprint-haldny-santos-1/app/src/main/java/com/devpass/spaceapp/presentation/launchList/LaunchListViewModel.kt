package com.devpass.spaceapp.presentation.launchList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.data.datasource.remote.toLaunchPresentation
import com.devpass.spaceapp.data.repository.FetchLaunchesRepository
import com.devpass.spaceapp.presentation.launchList.adapter.LaunchModel
import kotlinx.coroutines.launch

class LaunchListViewModel(private val repository: FetchLaunchesRepository) : ViewModel() {

    private val _list = MutableLiveData<List<LaunchModel>>()
    var list: LiveData<List<LaunchModel>> = _list

    fun getLaunches() {
        viewModelScope.launch {
            _list.value = repository.getsLaunches().docs.map {
                it.toLaunchPresentation(it)
            }
        }
    }

    companion object {
        fun providerFactory(repository: FetchLaunchesRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return LaunchListViewModel(repository = repository) as T
                }
            }
    }
}
