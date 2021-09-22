package com.gabrielribeiro.desafio_mobile.data.remote.model

import android.os.Parcelable
import com.gabrielribeiro.desafio_mobile.utils.BackEndUtils
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MovieResponse(
    val ancineId: String,
    val cast: String?,
    val city: String,
    val contentRating: String,
    val countIsPlaying: Int,
    val countryOrigin: String,
    val creationDate: String,
    val director: String,
    val distributor: String,
    val duration: String,
    val trailers : List<Trailer>?,
    val genres: List<String>,
    val id: String,
    val images: List<Image>,
    val inPreSale: Boolean,
    val isPlaying: Boolean,
    val isReexhibition: Boolean,
    val movieIdUrl: String,
    val nationalSiteURL: String,
    val originalTitle: String,
    val premiereDate: PremiereDate?,
    val priority: Int,
    val rating: Double,
    val rottenTomatoe: RottenTomatoe?,
    val siteURL: String,
    val synopsis: String,
    val title: String,
    val urlKey: String
) : Parcelable {
    val dateMillis : Long
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate)?.time } ?: 0

    private val premiereDateDate : Date?
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate) }

    val dateFormatted   : String? get() = premiereDateDate?.let { BackEndUtils.formatDateFormatPremiereDate(it) }

    val genresFormatted : String get() = if (genres.size > 1) {
        "${genres.first()}, ${genres.last()}"
    } else genres.first()


}