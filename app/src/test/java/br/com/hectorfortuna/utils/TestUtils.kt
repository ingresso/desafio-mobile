package br.com.hectorfortuna.utils

import br.com.hectorfortuna.ingressoapp.data.model.Item
import br.com.hectorfortuna.ingressoapp.data.model.PremiereDate
import org.mockito.kotlin.any

fun createItem(
    id: String = "1",
    title: String = "Hamlet",
    premiereDate: PremiereDate? = null,
    rating: Double = 5.0
): Item {
    return Item(
        b2BEventId = null,
        cities = listOf("Rio de Janeiro"),
        id = id,
        title = title,
        originalTitle = title,
        type = "Filme",
        movieIdUrl = "https://example.com/movie/$id",
        ancineId = "",
        countryOrigin = "Brasil",
        priority = 3200,
        contentRating = "12 anos",
        duration = "90",
        rating = rating,
        synopsis = "Some synopsis",
        cast = "Henrique Zanoni",
        director = "Cristiano Burlan",
        distributor = "",
        inPreSale = false,
        isReexhibition = false,
        urlKey = "hamlet",
        isPlaying = false,
        countIsPlaying = 0,
        premiereDate = premiereDate,
        creationDate = "0001-01-01T00:00:00Z",
        city = "Rio de Janeiro",
        siteURL = "https://www.ingresso.com/filme/$title",
        nationalSiteURL = "https://www.ingresso.com/filme/$title",
        images = listOf(),
        genres = listOf("Drama"),
        ratingDescriptors = listOf(),
        accessibilityHubs = listOf(),
        completeTags = listOf(),
        tags = listOf(),
        trailers = listOf(),
        partnershipType = null
    )
}

fun createPremiereDate(): PremiereDate {
    return PremiereDate(
        localDate = "2024-11-01T10:00:00Z",
        isToday = false,
        dayOfWeek = "Friday",
        dayAndMonth = "01 Nov",
        hour = "10:00",
        year = "2024"
    )
}
fun createComparisonPremiereDate(): PremiereDate {
    return PremiereDate(
        localDate = "2024-12-01T10:00:00Z",
        isToday = false,
        dayOfWeek = "Friday",
        dayAndMonth = "01 Nov",
        hour = "10:00",
        year = "2024"
    )
}