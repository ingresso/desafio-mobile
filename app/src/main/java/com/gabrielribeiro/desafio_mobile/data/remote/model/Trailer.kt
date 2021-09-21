package com.gabrielribeiro.desafio_mobile.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Trailer(val type : String, val url : String, val embeddedUrl : String) : Parcelable