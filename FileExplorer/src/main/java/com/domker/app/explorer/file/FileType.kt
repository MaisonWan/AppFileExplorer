package com.domker.app.explorer.file

/**
 * Created by wanlipeng on 2017/9/3.
 */
enum class FileType {
    TYPE_DIRECTORY, TYPE_TEXT, TYPE_IMAGE, TYPE_VIDEO, TYPE_APK, TYPE_PDF, UNKNOWN;

    companion object {
        /**
         * 获取文件类型的分类
         */
        fun getFileType(fileInfo: FileInfo) : FileType {
            if (fileInfo.file.isDirectory) {
                return TYPE_DIRECTORY
            }
            val fileName = fileInfo.fileName
            val index = fileName.lastIndexOf(".")
            val prefix = if (index == -1) "" else fileName.substring(index + 1)
            return when (prefix.toLowerCase()) {
                "txt", "log" -> TYPE_TEXT
                "jpg", "jpeg", "png", "bmp" -> TYPE_IMAGE
                "apk" -> TYPE_APK
                "mp4" -> TYPE_VIDEO
                "pdf" -> TYPE_PDF
                else -> UNKNOWN
            }
        }

        /**
         * 是否是安卓安装文件
         */
        fun isApkFile(fileName: String): Boolean {
            return fileName.endsWith(".apk")
        }
    }

}