package com.domker.app.explorer.file

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * 封装文件信息的实体类
 * Created by Maison on 2017/8/31.
 */
data class FileInfo(val file: File) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    /**
     * 获取文件的时间
     */
    fun getFileDate(): String {
        val stamp = file.lastModified()
        return formatter.format(Date(stamp))
    }
}

