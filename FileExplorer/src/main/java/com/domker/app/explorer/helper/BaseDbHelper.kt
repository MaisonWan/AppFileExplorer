package com.domker.app.explorer.helper

/**
 * Created by Maison on 2017/7/5.
 */

interface BaseDbHelper<out T> {
    fun query(sql: String): T

    fun getTables(): List<String>

    fun getColumns(table: String)

}