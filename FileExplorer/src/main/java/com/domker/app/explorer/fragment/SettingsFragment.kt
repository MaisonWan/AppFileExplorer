package com.domker.app.explorer.fragment

import android.content.Context
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.view.View
import com.domker.app.explorer.R

/**
 * 设置
 * Created by wanlipeng on 2017/9/3.
 */
class SettingsFragment : PreferenceFragment(), IActionFragment {
    private val SORT_KEY = "key_file_sort_type"
    private lateinit var mListPreference: ListPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.fe_settings_layout)

        mListPreference = findPreference(SORT_KEY) as ListPreference
        mListPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            loadFileSortType()
            false
        }
    }

    override fun onResume() {
        super.onResume()
        loadFileSortType()
    }

    private fun loadFileSortType() {
        val defaultValue = getString(R.string.fe_settings_sort_default_value)
        val value = preferenceManager.sharedPreferences.getString(SORT_KEY, defaultValue)
        val names = resources.getStringArray(R.array.file_sort_name)
        val index = mListPreference.findIndexOfValue(value)
        mListPreference.summary = names[index]
    }

    override fun init(context: Context, view: View) {

    }

    override fun onShown(context: Context) {

    }

    override fun initLayoutId(): Int = 0

    override fun onBackPressed(): Boolean = false
}