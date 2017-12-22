package com.domker.app.explorer.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Maison on 2017/12/15.
 */
@Entity(tableName = "path_favorite")
class PathFavorite {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "path")
    var path: String? = null
}