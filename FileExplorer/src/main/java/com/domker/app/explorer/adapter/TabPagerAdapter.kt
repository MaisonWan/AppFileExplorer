package com.domker.app.explorer.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.support.v13.app.FragmentPagerAdapter
import android.util.Log
import com.domker.app.explorer.fragment.AppInfoFragment
import com.domker.app.explorer.fragment.FileListFragment
import com.domker.app.explorer.fragment.SettingsFragment

/**
 * Created by wanlipeng on 2017/9/11.
 */
class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    /**
     * 显示tab的名字
     */
    var tabNames: Array<String>? = null
        set(value) {
            field = value
        }
        get() = field

    override fun getItem(position: Int): Fragment {
        Log.i("Adapter", "getItem: " + position)
        return when (position) {
            0 -> FileListFragment()
            1 -> AppInfoFragment()
            2 -> SettingsFragment()
            else -> FileListFragment()
        }
    }

    override fun getCount(): Int = tabNames?.size ?:0

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames?.get(position) ?: ""
    }

}