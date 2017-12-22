package com.domker.app.explorer.helper

import android.arch.persistence.room.Room
import android.content.Context
import java.io.File
import android.content.ContentValues
import com.domker.app.explorer.data.FileDatabase
import com.domker.app.explorer.data.PathFavorite
import com.domker.app.explorer.file.FileInfo


/**
 * Created by Maison on 2017/7/5.
 */
class DbManager {
    private val db: FileDatabase

    companion object {
        private val DB_NAME = "AppFileExplorer.db"

        fun getDataFiles(context: Context): List<File> {
            val list = context.databaseList()
            val dbs = ArrayList<File>()
            list.mapTo(dbs) { File(context.getDatabasePath(it).path) }
            return dbs
        }
    }

    constructor(context: Context) {
        db = Room.databaseBuilder(context, FileDatabase::class.java, DB_NAME).build()
    }

    /**
     * 添加收藏到列表
     */
    fun addPathFavorite(info: FileInfo): Long {
        val pathFavorite = PathFavorite()
        pathFavorite.path = info.filePath
        return db.pathFavoriteDao().insert(pathFavorite)
    }

    /**
     * 获取收藏列表
     */
    fun getAllPathFavorite(): List<FileInfo> {
        return db.pathFavoriteDao().getAllPath().map {
            FileInfo(File(it.path))
        }
    }
}