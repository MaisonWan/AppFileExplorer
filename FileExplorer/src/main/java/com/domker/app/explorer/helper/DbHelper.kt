package com.domker.app.explorer.helper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

/**
 * 数据库创建和查询等操作
 * Created by Maison on 2017/7/5.
 */

class DbHelper : BaseDbHelper<Cursor> {
    private var sqlHelper: SQLHelper

    constructor(context: Context, dbName: String) {
        sqlHelper = SQLHelper(context, dbName, null, 1)
    }

    override fun query(sql: String): Cursor {
        return sqlHelper.readableDatabase.rawQuery(sql, null)
    }

    fun readable(): SQLiteDatabase {
        return sqlHelper.readableDatabase
    }

    fun writable(): SQLiteDatabase {
        return sqlHelper.writableDatabase
    }

    override fun getTables(): List<String> {
        val sql = "show tables"
        val cursor = query(sql)
        val nameList = ArrayList<String>()
        while (cursor.moveToNext()) {
            val name = cursor.getString(0)
            nameList.add(name)
        }
        return nameList
    }

    override fun getColumns(table: String) {

    }

}