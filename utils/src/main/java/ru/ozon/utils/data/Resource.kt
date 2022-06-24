package ru.ozon.utils.data

/**
 * https://developer.android.com/topic/libraries/architecture/guide.html#addendum
 */
sealed class Resource<T> {
    abstract val data: T?

    data class Loading<T> internal constructor(override val data: T?) : Resource<T>()
    data class Error<T> internal constructor(override val data: T?, val desc: ErrorDesc) :
        Resource<T>()

    data class Success<T> internal constructor(override val data: T) : Resource<T>()

    data class ErrorDesc(
        val message: String? = null,
        val errorCode: Int? = null,
        val cause: Throwable? = null,
        val isNetwork: Boolean? = false
    )

    companion object {
        fun <T> newLoading(data: T? = null): Resource<T> = Loading(data)

        @Deprecated("Use newLoading instead.", ReplaceWith("Resource.newLoading(data)"))
        fun <T> newLoadingWithData(data: T): Resource<T> = Loading(data)
        fun <T> newSuccess(data: T): Resource<T> = Success(data)
        fun <T> newError(errorDesc: ErrorDesc): Resource<T> = newError(null, errorDesc)
        fun <T> newError(data: T?, errorDesc: ErrorDesc): Resource<T> = Error(data, errorDesc)
        fun <T> newError(data: T?, message: String? = null): Resource<T> =
            newError(data, ErrorDesc(message = message))

        fun <T> newError(message: String? = null): Resource<T> = newError(null, message)
    }

    fun onLoading(): Resource<T> = Loading(data)
    fun onSuccess(newData: T): Resource<T> = Success(newData)
    fun onError(errorDesc: ErrorDesc): Resource<T> = Error(data, errorDesc)
}

fun <T, R> Resource<T>.mapData(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Success -> {
            Resource.newSuccess(transform.invoke(data))
        }
        is Resource.Loading -> {
            val newData = data?.let(transform)
            newData?.let { Resource.newLoading(it) } ?: Resource.newLoading()
        }
        is Resource.Error -> {
            Resource.newError(data?.let(transform), desc)
        }
    }
}

@Suppress("RedundantUnitExpression")
fun <T> Resource<T>.mapDataToUnit(): Resource<Unit> {
    return mapData { Unit }
}

inline fun <T> Resource<T>.doOnSuccess(block: (data: T) -> Unit): Resource<T> {
    if (this is Resource.Success) {
        block(this.data)
    }

    return this
}

inline fun <T> Resource<T>.mapError(transform: (data: Resource.Error<T>) -> Resource.Error<T>): Resource<T> {
    if (this is Resource.Error) {
        transform(this)
    }

    return this
}