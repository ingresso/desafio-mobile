package com.jeanbarrossilva.ingresso.network.dto

import com.jeanbarrossilva.ingresso.model.Score

data class RottenTomatoe(val criticsScore: String, val audienceScore: String) {
    fun toScore(): Score {
        val (criticsScore, audienceScore) = criticsScore.ifBlank { null }?.toInt() to audienceScore.ifBlank { null }?.toInt()
        return Score(criticsScore, audienceScore)
    }
}