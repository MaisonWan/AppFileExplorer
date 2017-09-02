package com.domker.app.explorer.file

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.io.File


/**
 * Created by wanlipeng on 2017/9/3.
 */
object FileOpen {

    /**
     * 打开文件
     */
    fun openFile(context: Context, fileInfo: FileInfo) {
        val path = fileInfo.filePath
        val intent = when (fileInfo.fileType) {
            FileType.TYPE_IMAGE -> getImageFileIntent(path)
            FileType.TYPE_APK -> getApkFileIntent(path)
            FileType.TYPE_VIDEO -> getVideoFileIntent(path)
            else -> null
        }
        if (intent != null) {
            context.startActivity(intent)
        }
    }

    /**
     * android获取一个用于打开HTML文件的intent
     */
    fun getHtmlFileIntent(param: String): Intent {
        val uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(param).build()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "text/html")
        return intent
    }

    //android获取一个用于打开图片文件的intent
    fun getImageFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "image/*")
        return intent
    }

    //android获取一个用于打开PDF文件的intent
    fun getPdfFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/pdf")
        return intent
    }

    //android获取一个用于打开文本文件的intent
    fun getTextFileIntent(param: String, paramBoolean: Boolean): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (paramBoolean) {
            val uri1 = Uri.parse(param)
            intent.setDataAndType(uri1, "text/plain")
        } else {
            val uri2 = Uri.fromFile(File(param))
            intent.setDataAndType(uri2, "text/plain")
        }
        return intent
    }

    /**
     * android获取一个用于打开音频文件的intent
     */
    fun getAudioFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "audio/*")
        return intent
    }

    /**
     * android获取一个用于打开视频文件的intent
     */
    fun getVideoFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "video/*")
        return intent
    }

    /**
     * android获取一个用于打开CHM文件的intent
     */
    fun getChmFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/x-chm")
        return intent
    }

    /**
     * android获取一个用于打开Word文件的intent
     */
    fun getWordFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/msword")
        return intent
    }

    /**
     * android获取一个用于打开Excel文件的intent
     */
    fun getExcelFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/vnd.ms-excel")
        return intent
    }

    /**
     * android获取一个用于打开PPT文件的intent
     */
    fun getPptFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint")
        return intent
    }

    /**
     * 获取安装apk文件的intent
     */
    fun getApkFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.setDataAndType(Uri.fromFile(File(param)),
                "application/vnd.android.package-archive")
        return intent
    }
}