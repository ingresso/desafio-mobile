package com.gabrielribeiro.desafio_mobile.data.remote.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val type: String,
    val url: String
) : Parcelable