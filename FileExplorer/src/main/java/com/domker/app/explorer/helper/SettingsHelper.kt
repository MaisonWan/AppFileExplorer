package com.domker.app.explorer.helper

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by Maison on 2017/9/5.
 */
class SettingsHelper(context: Context) {
    private val sp = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        val KEY_FILE_SORT = "key_file_sort_type"
        val KEY_RECORD_LAST_PATH = "key_record_last_path"
    }

    /**
     * 是否需要记录上次的位置
     */
    fun isRecordLastPath(): Boolean {
        return sp.getBoolean(KEY_RECORD_LAST_PATH, false)
    }

    /**
     * 返回文件排序类型
     */
    fun getFileSortType(): String {
        return sp.getString(KEY_FILE_SORT, "1")
    }
}