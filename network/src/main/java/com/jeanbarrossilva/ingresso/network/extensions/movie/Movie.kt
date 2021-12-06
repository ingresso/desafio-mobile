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
        ?.let(Date::parse)
        ?.let { Date(it) }
    val title = Title(originalTitle, title)
    val imageUrl = ImageUrl(portrait = images[0].url, landscape = images[1].url)
    val (criticsScore, audienceScore) = rottenTomatoe?.criticsScore?.toInt() to rottenTomatoe?.audienceScore?.toInt()
    val score = Score(criticsScore, audienceScore)
    val cast = cast.split(", ")

    return Movie(id, premiereDate, title, imageUrl, genres, score, countryOrigin, director, cast, distributor, synopsis, siteURL)
}