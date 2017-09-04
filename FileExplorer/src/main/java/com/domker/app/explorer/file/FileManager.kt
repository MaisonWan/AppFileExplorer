package com.domker.app.explorer.file

import android.content.Context
import java.io.File
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable



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
            dir.listFiles()?.forEach {
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

    companion object {
        /**
         * 获取apk文件的图标
         */
        fun getApkIcon(context: Context, apkPath: String): Drawable? {
            val pm = context.packageManager
            val info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES)
            if (info != null) {
                val appInfo = info!!.applicationInfo
                appInfo.sourceDir = apkPath
                appInfo.publicSourceDir = apkPath
                try {
                    return appInfo.loadIcon(pm)
                } catch (e: OutOfMemoryError) {
                    e.printStackTrace()
                }
            }
            return null
        }
    }

}