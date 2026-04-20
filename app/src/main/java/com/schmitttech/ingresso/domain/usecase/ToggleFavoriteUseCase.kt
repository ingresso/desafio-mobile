package com.schmitttech.ingresso.domain.usecase

import com.schmitttech.ingresso.domain.repository.MoviesRepository

class ToggleFavoriteUseCase(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(id: String, isFavorite: Boolean) {
        repository.toggleFavorite(id, isFavorite)
    }
}
