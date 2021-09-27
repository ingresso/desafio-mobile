package com.gabrielribeiro.desafio_mobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gabrielribeiro.desafio_mobile.data.entity.MovieEntity

@Database(
    entities = [MovieEntity::class], version = 1
)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDAO() : MovieDao

}