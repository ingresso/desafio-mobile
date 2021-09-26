package com.gabrielribeiro.desafio_mobile.data.database

import androidx.room.TypeConverter
import com.gabrielribeiro.desafio_mobile.data.remote.models.Image
import com.gabrielribeiro.desafio_mobile.data.remote.models.PremiereDate
import com.gabrielribeiro.desafio_mobile.data.remote.models.Trailer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {

    @TypeConverter
    fun trailerListToJson (data : List<Trailer>?): String = Gson().toJson(data)

    @TypeConverter
    fun trailerJsonToList (data : String?): List<Trailer>? {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType: Type = object :
            TypeToken<List<Trailer>>() {}.type
        return Gson().fromJson<List<Trailer>>(data, listType)
    }

    @TypeConverter
    fun genderListToJson(data: List<String>): String = Gson().toJson(data)

    @TypeConverter
    fun genderJsonToList (data : String?): List<String> {
        val listType: Type = object :
            TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun imageListToJson(data: List<Image>): String = Gson().toJson(data)

    @TypeConverter
    fun imageJsonToList (data : String?): List<Image> {
        val listType: Type = object :
            TypeToken<List<Image>>() {}.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun premiereDateToJson(data: PremiereDate): String = Gson().toJson(data)

    @TypeConverter
    fun premiereDateJsonToObject (data : String?): PremiereDate {
        val date: Type = object :
            TypeToken<PremiereDate>() {}.type
        return Gson().fromJson(data, date)
    }


}