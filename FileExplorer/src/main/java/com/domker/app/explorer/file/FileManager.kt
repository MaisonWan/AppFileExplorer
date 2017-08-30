package com.domker.app.explorer.file

import java.io.File

/**
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
}