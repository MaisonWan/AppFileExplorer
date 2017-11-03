package com.domker.app.explorer.helper

import android.content.Context
import java.io.File
import android.content.ContentValues
import com.domker.app.explorer.file.FileInfo


/**
 * Created by Maison on 2017/7/5.
 */
class DbManager {
    private val dbHelper: DbHelper
    private val DB_NAME = "AppFileExplorer.db"
    private val TABLE_FAVORITE = "path_favorite"

    companion object {

        fun getDataFiles(context: Context): List<File> {
            val list = context.databaseList()
            val dbs = ArrayList<File>()
            list.mapTo(dbs) { File(context.getDatabasePath(it).path) }
            return dbs
        }
    }

    constructor(context: Context) {
        dbHelper = DbHelper(context, DB_NAME)
    }

    /**
     * 添加收藏到列表
     */
    fun addPathFavor(info: FileInfo): Long {
        val cv = ContentValues()
        cv.put("path", info.filePath)
        return dbHelper.writable().insert(TABLE_FAVORITE, null, cv)
    }

    /**
     * 获取收藏列表
     */
    fun getFavoritePath(): List<FileInfo> {
        val sql = "select * from $TABLE_FAVORITE"
        val cursor = dbHelper.readable().rawQuery(sql, null)
        val index = cursor.getColumnIndex("path")
        var paths = arrayListOf<FileInfo>()
        while (cursor.moveToNext()) {
            val info = FileInfo(File(cursor.getString(index)))
            paths.add(info)
        }
        return paths.toList()
    }
}