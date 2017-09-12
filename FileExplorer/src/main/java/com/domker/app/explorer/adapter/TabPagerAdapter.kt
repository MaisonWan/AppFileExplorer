package com.domker.app.explorer.adapter

import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.util.Log
import com.domker.app.explorer.fragment.FileListFragment
import com.domker.app.explorer.util.PhoneInfo

/**
 * Tab和ViewPager组合使用
 * Created by wanlipeng on 2017/9/11.
 */
class TabPagerAdapter(val context: Context, fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

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
        val fragment = FileListFragment()
        val bundle = Bundle()
        when (position) {
            0 -> bundle.putString(FileListFragment.KEY_DEFAULT_PATH, PhoneInfo.getSdCardPath()!!)
            1 -> bundle.putString(FileListFragment.KEY_DEFAULT_PATH, PhoneInfo.getInnerPath(context))
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = tabNames?.size ?:0

    override fun getPageTitle(position: Int): CharSequence {
        return tabNames?.get(position) ?: ""
    }

}