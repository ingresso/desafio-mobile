package com.jeanbarrossilva.ingresso.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUrl(val portrait: String?, val landscape: String?): Parcelable