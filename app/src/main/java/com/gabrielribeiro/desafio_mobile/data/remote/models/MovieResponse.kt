package com.gabrielribeiro.desafio_mobile.data.remote.models

import android.os.Parcelable
import com.gabrielribeiro.desafio_mobile.data.MovieParent
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    override val ancineId: String,
    override val cast: String?,
    override val city: String,
    override val contentRating: String,
    override val countIsPlaying: Int,
    override val countryOrigin: String,
    override val creationDate: String,
    override val director: String,
    override val distributor: String,
    override val duration: String,
    override val trailers: List<Trailer>?,
    override val genres: List<String>,
    override val id: String,
    override val images: List<Image>,
    override val inPreSale: Boolean,
    override val isPlaying: Boolean,
    override val isReexhibition: Boolean,
    override val movieIdUrl: String,
    override val nationalSiteURL: String,
    override val originalTitle: String,
    override val premiereDate: PremiereDate?,
    override val priority: Int,
    override val siteURL: String,
    override val synopsis: String,
    override val title: String,
    override val urlKey: String

) : MovieParent(),Parcelable {

    fun toMovieEntity() = MovieEntity(
        ancineId = ancineId,
        cast = cast,
        city = city,
        contentRating = contentRating,
        countIsPlaying = countIsPlaying,
        countryOrigin = countryOrigin,
        director = director,
        distributor = distributor,
        duration = duration,
        trailers = trailers,
        genres = genres,
        id = id,
        images = images,
        inPreSale = inPreSale,
        isPlaying = isPlaying,
        isReexhibition = isReexhibition,
        movieIdUrl = movieIdUrl,
        nationalSiteURL = nationalSiteURL,
        originalTitle = originalTitle,
        premiereDate = premiereDate,
        priority = priority,
        siteURL = siteURL,
        synopsis = synopsis,
        title = title,
        urlKey = urlKey,
        creationDate = creationDate

    )
    /*val dateMillis : Long
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate)?.time } ?: 0

    private val premiereDateDate : Date?
        get() = premiereDate?.let { BackEndUtils.parseDateTime(it.localDate) }

    val dateFormatted   : String? get() = premiereDateDate?.let { BackEndUtils.formatDateFormatPremiereDate(it) }

    val genresFormatted : String get() = if (genres.size > 1) {
        "${genres.first()}, ${genres.last()}"
    } else genres.first()*/


    /*@PrimaryKey(autoGenerate = true)
    val idDatabase : Long? = null,
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
    val urlKey: String*/

    /* val ancineId: String,
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
    val siteURL: String,
    val synopsis: String,
    val title: String,
    val urlKey: String*/

}