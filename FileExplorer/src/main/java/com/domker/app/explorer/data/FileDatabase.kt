package com.domker.app.explorer.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Maison on 2017/12/15.
 */
@Database(entities = [(PathFavorite::class)], version = 1)
abstract class FileDatabase: RoomDatabase() {

    abstract fun pathFavoriteDao(): PathFavoriteDao
}