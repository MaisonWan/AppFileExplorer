package com.domker.app.explorer.file

import android.content.Context
import android.text.format.Formatter
import java.io.File
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

/**
 * 封装文件信息的实体类
 * Created by Maison on 2017/8/31.
 */
data class FileInfo(val file: File) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    /**
     * 返回文件类型
     */
    val fileType: FileType
        get() = FileType.getFileType(this)

    /**
     * 获取文件的时间
     */
    fun getFileDate() = formatter.format(Date(file.lastModified()))!!

    /**
     * 是否是文件
     */
    fun isFile() = file.isFile

    /**
     * 获取文件尺寸
     */
    fun getFileSize(context: Context) = Formatter.formatFileSize(context, file.length())!!

}

