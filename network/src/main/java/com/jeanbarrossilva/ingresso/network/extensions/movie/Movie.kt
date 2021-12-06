package com.jeanbarrossilva.ingresso.network.extensions.movie

import com.jeanbarrossilva.ingresso.model.ImageUrl
import com.jeanbarrossilva.ingresso.model.Movie
import com.jeanbarrossilva.ingresso.model.Score
import com.jeanbarrossilva.ingresso.model.Title
import com.jeanbarrossilva.ingresso.network.dto.MovieDto
import java.util.Date

fun MovieDto.toMovie(): Movie {
    val id = id.toLong()
    val premiereDate = premiereDate?.localDate
        ?.run { substring(0, indexOf('T')) }
        ?.split('-')
        ?.map(String::toInt)
        ?.let { (year, month, day) -> Date(year, month, day) }
    val title = Title(originalTitle, title)
    val (portraitImageUrl, landscapeImageUrl) = images.firstOrNull()?.url to images.elementAtOrNull(1)?.url
    val imageUrl = ImageUrl(portraitImageUrl, landscapeImageUrl)
    val (criticsScore, audienceScore) =
        rottenTomatoe?.criticsScore?.ifBlank { null }?.toInt() to rottenTomatoe?.audienceScore?.ifBlank { null }?.toInt()
    val score = Score(criticsScore, audienceScore)
    val cast = cast.split(", ")

    return Movie(id, premiereDate, title, imageUrl, genres, score, countryOrigin, director, cast, distributor, synopsis, siteURL)
}