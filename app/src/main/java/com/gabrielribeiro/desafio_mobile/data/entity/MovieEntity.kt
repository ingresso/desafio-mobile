package com.gabrielribeiro.desafio_mobile.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabrielribeiro.desafio_mobile.data.MovieParent
import com.gabrielribeiro.desafio_mobile.data.remote.models.Image
import com.gabrielribeiro.desafio_mobile.data.remote.models.MovieResponse
import com.gabrielribeiro.desafio_mobile.data.remote.models.PremiereDate
import com.gabrielribeiro.desafio_mobile.data.remote.models.Trailer
import com.gabrielribeiro.desafio_mobile.utils.BackEndUtils
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(
    tableName = "movies"
)
@Parcelize
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val idDatabase : Int? = null,
    override val ancineId: String,
    override val cast: String?,
    override val city: String,
    override val contentRating: String,
    override val countIsPlaying: Int,
    override val countryOrigin: String,
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
    override val urlKey: String,
    override val creationDate: String
) : MovieParent(), Parcelable {

    fun toMovieResponse() = MovieResponse(
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
}