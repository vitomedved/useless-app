package com.example.lastfmuselessapp.domain.model

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>()
    class Idle<T>(data: T? = null): Resource<T>()

    // TODO can I remove this or am I still using it?
    fun <A> map(mapper: (T?) -> A?): Resource<A> {
        return when (this) {
            is Success -> Success(mapper(data)!!)
            is Error -> Error(message = message!!, data = mapper(data))
            is Loading -> Loading(data = mapper(data))
            is Idle -> Idle(data = mapper(data))
        }
    }
}
