package com.domker.app.explorer.helper

import android.content.Context
import java.io.File

/**
 * Created by Maison on 2017/7/5.
 */
class DbManager {

    companion object {

        fun getDataFiles(context: Context): List<File> {
            val list = context.databaseList()
            val dbs = ArrayList<File>()
            list.mapTo(dbs) { File(context.getDatabasePath(it).path) }
            return dbs
        }
    }

}