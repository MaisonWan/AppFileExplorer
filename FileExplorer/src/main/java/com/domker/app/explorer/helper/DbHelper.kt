package com.domker.app.explorer.helper

import android.content.Context
import android.database.Cursor

/**
 * Created by Maison on 2017/7/5.
 */

class DbHelper : BaseDbHelper<Cursor> {
    var sqlHepler: SQLHelper

    constructor(context: Context, dbName: String) {
        sqlHepler = SQLHelper(context, dbName, null, 1)
    }

    override fun query(sql: String): Cursor {
        return sqlHepler.readableDatabase.rawQuery(sql, null)
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