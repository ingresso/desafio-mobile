package br.com.hectorfortuna.ingressoapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllMoviesResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<Item>
):Parcelable