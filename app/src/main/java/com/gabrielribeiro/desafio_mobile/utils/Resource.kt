package com.gabrielribeiro.desafio_mobile.utils


sealed class Resource<T>(val data : T? = null,  val exception: Throwable? = null, val message : String? = null)  {

    class Loading<T> : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data)
    class Failure<T>(exception: Throwable?, message: String?) : Resource<T>(null, exception, message)
}