package com.domker.app.explorer.file

import android.os.AsyncTask

/**
 * Created by wanlipeng on 2017/9/2.
 */
class FileLoader(private val callback: FileLoaderCallback) : AsyncTask<String, Void, List<FileInfo>>() {
    private val fileManager: FileManager = FileManager()

    override fun doInBackground(vararg args: String?): List<FileInfo> {
        val path = args[0] as String
        return fileManager.getFileList(path)
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