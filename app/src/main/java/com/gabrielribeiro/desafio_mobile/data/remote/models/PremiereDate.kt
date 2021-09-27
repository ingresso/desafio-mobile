package com.gabrielribeiro.desafio_mobile.data.remote.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PremiereDate(val localDate : String, val isToday : Boolean,
                        val dayOfWeek : String, val dayAndMonth : String,
                        val hour : String, val year : Int) : Parcelable
