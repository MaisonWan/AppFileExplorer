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
     * 文件路径
     */
    val filePath: String
        get() = file.absolutePath

    /**
     * 是否是跳转到上层目录的标示
     */
    var isJumpParentPath: Boolean = false
        get() = field
        set(value) {
            field = value
        }

    /**
     * 文件名称
     */
    val fileName: String
        get() = file.name

    /**
     * 获取文件的时间
     */
    fun getFileDate() = if (isJumpParentPath) "" else formatter.format(Date(file.lastModified()))!!

    /**
     * 是否是文件
     */
    fun isFile() = file.isFile

    /**
     * 获取文件尺寸
     */
    fun getFileSize(context: Context) = Formatter.formatFileSize(context, file.length())!!

}

