package com.roque.meuingresso.domain.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("imageFeatured") val imageFeatured: String,
    @SerializedName("images") val images: List<ImageDto>,
    @SerializedName("genres") val genres: List<String>
)