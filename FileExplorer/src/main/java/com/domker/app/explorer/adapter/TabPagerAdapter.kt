package com.domker.app.explorer.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.util.Log
import com.domker.app.explorer.fragment.FileListFragment
import com.domker.app.explorer.fragment.FileListFragment.Companion.KEY_DEFAULT_PATH
import com.domker.app.explorer.fragment.FileListFragment.Companion.KEY_IS_FAVORITE
import com.domker.app.explorer.util.PhoneInfo

/**
 * Tab和ViewPager组合使用
 * Created by wanlipeng on 2017/9/11.
 */
class TabPagerAdapter(val context: Context, fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
    private lateinit var fragments : Array<FileListFragment?>

    /**
     * 显示tab的名字
     */
    var tabNames: Array<String>? = null
        set(value) {
            field = value
            fragments = arrayOfNulls(field!!.size)
        }


    override fun getItem(position: Int): Fragment {
        Log.i("Adapter", "getItem: " + position)
        if (fragments[position] == null) {
            fragments[position] = FileListFragment()
        }
        val fragment = fragments[position]
        val bundle = Bundle()
        when (position) {
            0 -> bundle.putString(KEY_DEFAULT_PATH, PhoneInfo.getSdCardPath()!!)
            1 -> bundle.putString(KEY_DEFAULT_PATH, PhoneInfo.getInnerPath(context))
            2 -> bundle.putBoolean(KEY_IS_FAVORITE, true)
        }
        fragment?.arguments = bundle
        return fragment!!
    }

    override fun getCount(): Int = tabNames?.size ?:0

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames?.get(position) ?: ""
    }

    fun getCurrentFragment(position: Int): FileListFragment? {
        return if (position >= fragments.size) null else fragments[position]
    }
}