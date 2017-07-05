package com.domker.db.sqlviewer.helper

/**
 * Created by Maison on 2017/7/5.
 */

interface BaseDbHelper<T> {
    fun query(sql: String): T

    fun getTables(): List<String>

    fun getColumns(table: String)

}