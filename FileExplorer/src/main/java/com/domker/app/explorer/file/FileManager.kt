package com.domker.app.explorer.file

import java.io.File

/**
 * 封装文件相关操作的类
 * Created by Maison on 2017/8/31.
 */
class FileManager {

    /**
     * 获取目录中的文件和文件夹列表
     */
    fun getFileList(path: String): List<FileInfo> {
        val fileList = ArrayList<FileInfo>()
        val dir = File(path)
        if (dir.isDirectory) {
            dir.listFiles().forEach {
                val fileInfo = FileInfo(it)
                fileList.add(fileInfo)
            }
        }
        return fileList
    }

    /**
     * 获取该目录下的文件信息
     */
    fun getFileList(file: File): List<FileInfo> {
        return getFileList(file.absolutePath)
    }
}