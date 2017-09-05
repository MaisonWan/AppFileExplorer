package com.domker.app.explorer.file

import android.os.AsyncTask

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
        return fileList
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