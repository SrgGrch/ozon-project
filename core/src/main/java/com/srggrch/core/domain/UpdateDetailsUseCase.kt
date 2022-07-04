package com.srggrch.core.domain

import ru.ozon.utils.data.Resource

interface UpdateDetailsUseCase {
    suspend fun execute(): Resource<Unit>
}