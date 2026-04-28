package com.roque.meuingresso.domain.model

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String
)
