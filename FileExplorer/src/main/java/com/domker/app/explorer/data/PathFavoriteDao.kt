package com.domker.app.explorer.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Maison on 2017/12/15.
 */
@Dao
interface PathFavoriteDao {

    @Query("select * from path_favorite")
    fun getAllPath(): List<PathFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pathFavorite: PathFavorite): Long


}