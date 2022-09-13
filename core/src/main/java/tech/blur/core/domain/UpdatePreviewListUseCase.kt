package tech.blur.core.domain

import tech.blur.utils.data.Resource

interface UpdatePreviewListUseCase {
    suspend fun execute(): Resource<Unit>
}