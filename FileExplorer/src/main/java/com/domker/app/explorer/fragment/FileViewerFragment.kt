package com.domker.app.explorer.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.domker.app.explorer.R
import com.domker.app.explorer.adapter.TabPagerAdapter

/**
 * Created by wanlipeng on 2017/9/11.
 */
class FileViewerFragment : BaseFragment() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    private val mTabNames = arrayOf("外部存储", "内置目录", "收藏")

    override fun init(context: Context, view: View) {
        initViews(view)
        initTab(view)
    }

    @SuppressLint("NewApi")
    private fun initViews(view: View) {
        mViewPager = view.findViewById(R.id.viewPager)
        val adapter = TabPagerAdapter(fragmentManager)
        adapter.tabNames = mTabNames
        mViewPager.adapter = adapter
    }

    private fun initTab(view: View) {
        mTabLayout = view.findViewById(R.id.tab_layout)
        // 此处不必自己声明tab。该方法中已经实现
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun initAssistButtonDrawable(context: Context): Drawable? {
        return null
    }

    override fun onAssistButtonClick(view: View) {

    }

    override fun onShown(context: Context) {
        mViewPager.currentItem = 1
    }

    override fun initLayoutId(): Int = R.layout.fe_fragment_file_viewer

    override fun onBackPressed(): Boolean {
        return false
    }

}