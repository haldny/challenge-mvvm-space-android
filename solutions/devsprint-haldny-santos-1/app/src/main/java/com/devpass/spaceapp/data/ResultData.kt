package com.devpass.spaceapp.data

sealed interface ResultData<out R> {
    data class Success<out T>(val data: T) : ResultData<T>
    data class Error(val throwable: Throwable) : ResultData<Nothing>
}
