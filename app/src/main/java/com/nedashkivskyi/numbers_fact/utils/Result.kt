package com.nedashkivskyi.numbers_fact.utils

sealed class Result<T> (
    data: T?,
    message:String?
){
    class Success<T>(val data: T?):
        Result<T>(data = data, message = null)
    class Error<T>(val message: String?):
        Result<T>(data = null, message = message)
}
