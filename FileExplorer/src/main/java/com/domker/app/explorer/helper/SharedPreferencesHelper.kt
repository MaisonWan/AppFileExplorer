package com.domker.app.explorer.helper

import android.content.Context

/**
 * 存储
 * Created by wanlipeng on 2017/9/3.
 */
class SharedPreferencesHelper private constructor(context: Context) {
    companion object {
        private var instance: SharedPreferencesHelper? = null
        /**
         * 单例
         */
        fun getInstance(context: Context): SharedPreferencesHelper {
            if (instance == null) {
                synchronized(SharedPreferencesHelper::class) {
                    if (instance == null) {
                        instance = SharedPreferencesHelper(context)
                    }
                }
            }
            return instance!!
        }
    }

    private val KEY_DEFAULT_PATH = "default_path"
    private val KEY_DEFAULT_ITEM_ID = "default_item_id"

    private val sp = context.getSharedPreferences("AppFileExplorer", Context.MODE_PRIVATE)

    /**
     * 获取默认加载路径
     */
    fun getDefaultPath(defaultPath: String): String {
        return sp.getString(KEY_DEFAULT_PATH, defaultPath)
    }

    /**
     * 存储默认的路径
     */
    fun saveDefaultPath(path: String) {
        sp.edit().putString(KEY_DEFAULT_PATH, path).apply()
    }

    /**
     * 获取默认显示Item的id
     */
    fun getDefaultItemId(): Int {
        return sp.getInt(KEY_DEFAULT_ITEM_ID, 1)
    }

    /**
     * 存储默认的Item Id
     */
    fun saveDefaultItemId(id: Int) {
        sp.edit().putInt(KEY_DEFAULT_ITEM_ID, id).apply()
    }
}