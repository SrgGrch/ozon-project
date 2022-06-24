package com.srggrch.core.data.api

import ru.ozon.utils.data.Resource
import java.lang.Exception

internal suspend fun <T> Api.runRequest(block: suspend (Api.() -> T)): Resource<T> {
    return try {
        Resource.newSuccess(block(this))
    } catch (e: Exception) {
        Resource.newError(Resource.ErrorDesc(cause = e))
    }
}