package com.domker.app.explorer.file

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import java.io.File
import android.support.v4.content.FileProvider


/**
 * Created by wanlipeng on 2017/9/3.
 */
object FileOpen {

    /**
     * 打开文件
     */
    fun openFile(context: Context, fileInfo: FileInfo) {
        val path = fileInfo.filePath
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getContentUri(context, path)
        } else {
            Uri.parse(path)
        }
        val intent = when (fileInfo.fileType) {
            FileType.TYPE_TEXT -> getTextFileIntent(uri)
            FileType.TYPE_IMAGE -> getImageFileIntent(uri)
            FileType.TYPE_APK -> getApkFileIntent(uri)
            FileType.TYPE_VIDEO -> getVideoFileIntent(uri)
            else -> null
        }
        if (intent != null) {
            // 读取权限
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
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

    /**
     * android获取一个用于打开图片文件的intent
     */
    private fun getImageFileIntent(uri: Uri): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(uri, "image/*")
        return intent
    }

    /**
     * android获取一个用于打开PDF文件的intent
     */
    fun getPdfFileIntent(param: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val uri = Uri.fromFile(File(param))
        intent.setDataAndType(uri, "application/pdf")
        return intent
    }

    /**
     * android获取一个用于打开文本文件的intent
     */
    private fun getTextFileIntent(uri: Uri): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(uri, "text/plain")
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
    private fun getVideoFileIntent(uri: Uri): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("oneshot", 0)
        intent.putExtra("configchange", 0)
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
    private fun getApkFileIntent(uri: Uri): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        return intent
    }

    /**
     * 获取uri
     */
    private fun getContentUri(context: Context, fileName: String): Uri {
        return FileProvider.getUriForFile(context, "com.domker.app.explorer.fileprovider", File(fileName))
    }

}

