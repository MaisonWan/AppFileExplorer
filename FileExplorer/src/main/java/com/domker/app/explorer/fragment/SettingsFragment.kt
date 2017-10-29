package com.domker.app.explorer.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.domker.app.explorer.R
import com.domker.app.explorer.helper.SettingsHelper.Companion.KEY_FILE_SORT
import com.domker.app.explorer.util.hideMenu

/**
 * 设置
 * Created by wanlipeng on 2017/9/3.
 */
class SettingsFragment : PreferenceFragment(), IActionFragment {

    private lateinit var mListPreference: ListPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.fe_settings_layout)

        mListPreference = findPreference(KEY_FILE_SORT) as ListPreference
        mListPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            loadFileSortType()
            false
        }
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        loadFileSortType()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.hideMenu()
    }

    private fun loadFileSortType() {
        val defaultValue = getString(R.string.fe_settings_sort_default_value)
        val value = preferenceManager.sharedPreferences.getString(KEY_FILE_SORT, defaultValue)
        val names = resources.getStringArray(R.array.file_sort_name)
        val index = Math.max(mListPreference.findIndexOfValue(value), 0) // 防止找不到-1越界
        mListPreference.summary = names[index]
    }

    override fun init(context: Context, view: View) {

    }

    override fun initAssistButtonDrawable(context: Context): Drawable? = null

    override fun onAssistButtonClick(view: View) {
        // ignore
    }

    override fun onShown(context: Context) {

    }

    override fun initLayoutId(): Int = 0

    override fun onBackPressed(): Boolean = false
}