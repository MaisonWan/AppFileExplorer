package com.domker.app.explorer.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.LruCache
import com.domker.app.explorer.R
import com.domker.app.explorer.file.FileInfo
import com.domker.app.explorer.file.FileManager
import com.domker.app.explorer.file.FileType


/**
 * 使用LRU算法缓存图标
 * Created by wanlipeng on 2017/9/3.
 */
class IconLruCache(val context: Context) {
    private val size: Int = 1024 * 1024 * 30
    private var mIconCache: LruCache<String, Drawable> = object : LruCache<String, Drawable>(size) {
        override fun sizeOf(key: String?, value: Drawable?): Int {
            return super.sizeOf(key, value)
        }
    }

    /**
     * 从内存缓存中获取一个Bitmap
     * @param fileInfo
     * @return
     */
    fun getDrawable(fileInfo: FileInfo): Drawable {
        val path = fileInfo.file.absolutePath
        var key = if (fileInfo.isFile() && FileType.isApkFile(path)) {
            path
        } else {
            fileInfo.fileType.name
        }
        var drawable = mIconCache[key]
        if (drawable == null) {
            drawable = loadDrawable(fileInfo)
            mIconCache.put(key, drawable)
        }
        return drawable!!
    }

    /**
     * 加载资源
     */
    private fun loadDrawable(fileInfo: FileInfo) : Drawable {
        if (FileType.isApkFile(fileInfo.file.name)) {
            return FileManager.getApkIcon(context, fileInfo.file.absolutePath)!!
        }
        return when (fileInfo.fileType) {
            FileType.TYPE_DIRECTORY -> getResDrawable(R.drawable.fe_ic_folder_black)
            FileType.TYPE_IMAGE -> getResDrawable(R.drawable.fe_ic_image_black)
            else -> getResDrawable(R.drawable.ic_menu_camera)
        }
    }

    /**
     * 根据资源ID加载
     */
    private fun getResDrawable(resId: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= 21) context.getDrawable(resId) else
            context.resources.getDrawable(resId)
    }
}