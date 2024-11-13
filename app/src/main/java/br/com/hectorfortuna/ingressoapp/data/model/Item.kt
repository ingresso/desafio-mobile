package br.com.hectorfortuna.ingressoapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @SerializedName("b2BEventId")
    val b2BEventId: String?,
    @SerializedName("cities")
    val cities: List<String>?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("originalTitle")
    val originalTitle: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("movieIdUrl")
    val movieIdUrl: String?,
    @SerializedName("ancineId")
    val ancineId: String?,
    @SerializedName("countryOrigin")
    val countryOrigin: String?,
    @SerializedName("priority")
    val priority: Int?,
    @SerializedName("contentRating")
    val contentRating: String?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("synopsis")
    val synopsis: String?,
    @SerializedName("cast")
    val cast: String?,
    @SerializedName("director")
    val director: String?,
    @SerializedName("distributor")
    val distributor: String?,
    @SerializedName("inPreSale")
    val inPreSale: Boolean?,
    @SerializedName("isReexhibition")
    val isReexhibition: Boolean?,
    @SerializedName("urlKey")
    val urlKey: String?,
    @SerializedName("isPlaying")
    val isPlaying: Boolean?,
    @SerializedName("countIsPlaying")
    val countIsPlaying: Int?,
    @SerializedName("premiereDate")
    val premiereDate: PremiereDate?,
    @SerializedName("creationDate")
    val creationDate: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("siteURL")
    val siteURL: String?,
    @SerializedName("nationalSiteURL")
    val nationalSiteURL: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("ratingDescriptors")
    val ratingDescriptors: List<String>?,
    @SerializedName("accessibilityHubs")
    val accessibilityHubs: List<String>?,
    @SerializedName("completeTags")
    val completeTags: List<String>?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("trailers")
    val trailers: List<Trailer>?,
    @SerializedName("partnershipType")
    val partnershipType: String?,
):Parcelable