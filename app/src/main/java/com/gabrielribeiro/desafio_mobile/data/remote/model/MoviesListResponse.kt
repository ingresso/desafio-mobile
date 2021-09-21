package com.gabrielribeiro.desafio_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("items")
    val movieResponses: List<MovieResponse>
)