package com.domker.app.explorer.file

import android.os.AsyncTask
import java.io.File

/**
 * Created by wanlipeng on 2017/9/2.
 */
class FileLoader(private val callback: FileLoaderCallback) : AsyncTask<String, Void, List<FileInfo>>() {
    private val fileManager: FileManager = FileManager()

    /**
     * 文件排序
     */
    var fileSortType: String = "1"
        set(value) {
            field = value
        }

    override fun doInBackground(vararg args: String?): List<FileInfo> {
        val path = args[0] as String
        var fileList = fileManager.getFileList(path)
        fileList = when (fileSortType) {
            "1" -> fileList.sortedBy { it.fileName }
            "2" -> fileList.sortedBy { it.file.length() }
            "3" -> fileList.sortedBy { it.file.lastModified() }
            "4" -> fileList.sortedBy { it.isFile() }
            else -> fileList
        }
        if (path == "/") {
            return fileList
        }
        val arrayList = ArrayList<FileInfo>()
        arrayList.add(0, createParentDirFileInfo())
        arrayList.addAll(fileList)
        return arrayList
    }

    /**
     * 创建跳转会上一层目录
     */
    private fun createParentDirFileInfo(): FileInfo {
        val info = FileInfo(File(".."))
        info.isJumpParentPath = true
        return info
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: List<FileInfo>?) {
        super.onPostExecute(result)
        callback?.onReceiveResult(result!!)
    }

    interface FileLoaderCallback {

        /**
         * 接受到结果的回调
         */
        fun onReceiveResult(result: List<FileInfo>)
    }
}