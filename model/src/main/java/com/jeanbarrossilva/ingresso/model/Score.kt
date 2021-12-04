package com.jeanbarrossilva.ingresso.model

import androidx.annotation.IntRange

data class Score(@IntRange(from = 0, to = 100) val critics: Int, @IntRange(from = 0, to = 100) val audience: Int)