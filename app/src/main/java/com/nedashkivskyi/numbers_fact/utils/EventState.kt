package com.nedashkivskyi.numbers_fact.utils

sealed class EventState<T>(
    val data: T?,
    val message: String?,
    val isLoading: Boolean?
) {
    class Success<T>(data: T?): EventState<T>(data = data, message = null, isLoading = null)
    class Error<T>(message: String?) : EventState<T>(data = null, message = message,  isLoading = null)
    class Loading<T>(isLoading: Boolean) : EventState<T>(data = null, message = null, isLoading = isLoading)
    class Empty<T> : EventState<T>(data = null, message = null, isLoading = null)
}