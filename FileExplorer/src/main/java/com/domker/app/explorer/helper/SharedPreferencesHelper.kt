package com.domker.app.explorer.helper

import android.content.Context
import com.domker.app.explorer.util.PhoneInfo

/**
 * 存储
 * Created by wanlipeng on 2017/9/3.
 */
class SharedPreferencesHelper(context: Context) {
    private val KEY_DEFAULT_PATH = "default_path"

    private val sp = context.getSharedPreferences("FileExplorer", Context.MODE_PRIVATE)

    /**
     * 获取默认加载路径
     */
    fun getDefaultPath(): String {
        return sp.getString(KEY_DEFAULT_PATH, PhoneInfo.getSdCardPath())
    }

    /**
     * 存储默认的路径
     */
    fun saveDefaultPath(path: String) {
        sp.edit().putString(KEY_DEFAULT_PATH, path).apply()
    }
}