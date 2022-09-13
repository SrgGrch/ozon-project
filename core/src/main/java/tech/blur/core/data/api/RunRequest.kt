package tech.blur.core.data.api

import tech.blur.utils.data.Resource

internal suspend fun <T> Api.runRequest(block: suspend (Api.() -> T)): Resource<T> {
    return try {
        Resource.newSuccess(block(this))
    } catch (e: Exception) {
        Resource.newError(Resource.ErrorDesc(cause = e))
    }
}