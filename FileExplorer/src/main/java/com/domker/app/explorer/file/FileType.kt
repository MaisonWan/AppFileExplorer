package com.domker.app.explorer.file

/**
 * Created by wanlipeng on 2017/9/3.
 */
enum class FileType {
    TYPE_DIRECTORY, TYPE_IMAGE;

    companion object {
        /**
         * 获取文件类型的分类
         */
        fun getFileType(fileInfo: FileInfo) : FileType {
            if (fileInfo.file.isDirectory) {
                return TYPE_DIRECTORY
            }
            return TYPE_DIRECTORY
        }

        /**
         * 是否是安卓安装文件
         */
        fun isApkFile(fileName: String): Boolean {
            return fileName.endsWith(".apk")
        }
    }

}