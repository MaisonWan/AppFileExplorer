package com.domker.db.sqlviewer.helper

import android.content.Context
import java.io.File
import java.io.FileFilter

/**
 * Created by Maison on 2017/7/5.
 */
class DbManager {

    fun getDataFiles(context: Context): List<File> {
        val file = File(context.filesDir.path)
        return file.listFiles(FileFilter { it.endsWith(".db") }).asList()
    }


}