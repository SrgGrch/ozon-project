package com.srggrch.core.domain

import ru.ozon.utils.data.Resource

interface UpdatePreviewListUseCase {
    suspend fun execute(): Resource<Unit>
}