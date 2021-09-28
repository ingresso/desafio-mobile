package com.gabrielribeiro.desafio_mobile.data

import com.gabrielribeiro.desafio_mobile.data.remote.models.Image
import com.gabrielribeiro.desafio_mobile.data.remote.models.PremiereDate
import com.gabrielribeiro.desafio_mobile.data.remote.models.Trailer
import com.gabrielribeiro.desafio_mobile.utils.BackEndUtils
import java.util.*

abstract class MovieParent {
    abstract val ancineId: String
    abstract val cast: String?
    abstract val city: String
    abstract val contentRating: String
    abstract val countIsPlaying: Int
    abstract val countryOrigin: String
    abstract val creationDate: String
    abstract val director: String
    abstract val distributor: String
    abstract val duration: String
    abstract val trailers : List<Trailer>?
    abstract val genres: List<String>
    abstract val id: String
    abstract val images: List<Image>
    abstract val inPreSale: Boolean
    abstract val isPlaying: Boolean
    abstract val isReexhibition: Boolean
    abstract val movieIdUrl: String
    abstract val nationalSiteURL: String
    abstract val originalTitle: String
    abstract val premiereDate: PremiereDate?
    abstract val priority: Int
    abstract val siteURL: String
    abstract val synopsis: String
    abstract val title: String
    abstract val urlKey: String

    val dateMillis : Long
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate)?.time } ?: 0

    private val premiereDateDate : Date?
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate) }

    val dateFormatted   : String? get() = premiereDateDate?.let { BackEndUtils.formatDateFormatPremiereDate(it) }

    val genresFormatted : String get() = if (genres.size > 1) {
        "${genres.first()}, ${genres.last()}"
    } else genres.first()
}