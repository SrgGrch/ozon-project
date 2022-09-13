package tech.blur.core.domain

import tech.blur.utils.data.Resource

interface UpdateDetailsUseCase {
    suspend fun execute(): Resource<Unit>
}