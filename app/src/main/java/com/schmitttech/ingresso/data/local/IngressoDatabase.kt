package com.schmitttech.ingresso.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.schmitttech.ingresso.data.local.dao.MovieDao
import com.schmitttech.ingresso.data.local.entity.MovieEntity

/**
 * Main Room database for the Ingresso app.
 * Version 1 — initial schema with movies cache and favorites.
 */
@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IngressoDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
