package com.jeanbarrossilva.ingresso.network.dto

import com.jeanbarrossilva.ingresso.extensions.any.orIf
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.model.Score
import com.jeanbarrossilva.ingresso.model.Title
import com.jeanbarrossilva.ingresso.network.extensions.image.toImageUrl

data class MovieDto(
    val id: String,
    val title: String,
    val originalTitle: String,
    val countryOrigin: String,
    val synopsis: String,
    val cast: String,
    val director: String,
    val distributor: String,
    val inPreSale: Boolean,
    val premiereDate: PremiereDateDto?,
    val siteURL: String,
    val images: List<Image>,
    val genres: List<String>,
    val rottenTomatoe: RottenTomatoe?
) {
    fun toMovie(): Movie {
        val id = id.toLong()
        val premiereDate = premiereDate?.toDate()
        val (originalTitle, localizedTitle) = originalTitle.trim() to title.trim()
        val title = Title(originalTitle, localizedTitle)
        val imageUrl = images.toImageUrl()
        val score = rottenTomatoe?.toScore() ?: Score.empty
        val genres = genres.map(String::trim).filter(String::isNotBlank)
        val country = countryOrigin.trim().ifBlank { null }
        val director = director.trim().ifBlank { null }
        val cast = cast.split(", ").map(String::trim).filter(String::isNotBlank)
        val distributor = distributor.trim().orIf<String?>({ this == "Sem Distribuidor" }) { null }
        val synopsis = synopsis.trim().ifBlank { null }

        return Movie(
            id,
            premiereDate,
            inPreSale,
            title,
            imageUrl,
            genres,
            score,
            country,
            director,
            cast,
            distributor,
            synopsis,
            siteURL
        )
    }
}