package com.domker.app.explorer.hostapp

import android.content.Context
import android.graphics.drawable.Drawable

/**
 * Created by wanlipeng on 2017/8/31.
 */
object HostApp {

    fun getHostAppIcon(context: Context): Drawable? {
        return try {
            val packageName = context.packageName
            val appInfo = context.packageManager.getApplicationInfo(packageName, 0)
            appInfo.loadIcon(context.packageManager)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 获取应用程序的包名
     */
    fun getHostAppPackage(context: Context): String = context.packageName

    /**
     * 获取程序名词
      */
    fun getHostAppName(context: Context): String? {
        return try {
            val packageName = context.packageName
            val appInfo = context.packageManager.getApplicationInfo(packageName, 0)
            appInfo.loadLabel(context.packageManager).toString()
        } catch (e: Exception) {
            null
        }
    }
}