package com.roque.meuingresso.data.model

import com.google.gson.annotations.SerializedName
import com.roque.meuingresso.domain.model.MovieDto

data class MovieResponse(
    @SerializedName("items") val items: List<MovieDto>
)
