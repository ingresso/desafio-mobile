package br.com.hectorfortuna.ingressoapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PremiereDate(
    @SerializedName("localDate")
    val localDate: String,
    @SerializedName("isToday")
    val isToday: Boolean,
    @SerializedName("dayOfWeek")
    val dayOfWeek: String,
    @SerializedName("dayAndMonth")
    val dayAndMonth: String,
    @SerializedName("hour")
    val hour: String,
    @SerializedName("year")
    val year: String
):Parcelable