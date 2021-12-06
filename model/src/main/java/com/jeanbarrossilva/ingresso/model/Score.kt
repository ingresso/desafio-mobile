package com.jeanbarrossilva.ingresso.model

import android.os.Parcelable
import androidx.annotation.IntRange
import kotlinx.parcelize.Parcelize

@Parcelize
data class Score(@IntRange(from = 0, to = 100) val critics: Int?, @IntRange(from = 0, to = 100) val audience: Int?): Parcelable