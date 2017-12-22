package com.domker.app.explorer.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.design.widget.TabLayout
import android.view.View
import com.domker.app.explorer.R
import com.domker.app.explorer.adapter.TabPagerAdapter
import com.domker.app.explorer.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.fe_fragment_file_viewer.*

/**
 * Created by wanlipeng on 2017/9/11.
 */
class FileViewerFragment : BaseFragment() {
    private lateinit var mTabLayout: TabLayout
    private lateinit var mAdapter: TabPagerAdapter
    private lateinit var mSpHelper: SharedPreferencesHelper

    private val mTabNames = arrayOf("外部存储", "内置目录", "收藏")

    override fun init(context: Context, view: View) {
        initViews()
        initTab(view)
    }

    @SuppressLint("NewApi")
    private fun initViews() {
        mSpHelper = SharedPreferencesHelper.getInstance(activity)
        mAdapter = TabPagerAdapter(context, fragmentManager)
        mAdapter.tabNames = mTabNames
        viewPager.adapter = mAdapter
        viewPager.currentItem = mSpHelper.getDefaultItemId()
    }

    private fun initTab(view: View) {
        mTabLayout = view.findViewById(R.id.tab_layout)
        // 此处不必自己声明tab。该方法中已经实现
        mTabLayout.setupWithViewPager(viewPager)
    }

    override fun initAssistButtonDrawable(context: Context): Drawable? {
        return null
    }

    override fun onAssistButtonClick(view: View) {

    }

    override fun onShown(context: Context) {

    }

    override fun onPause() {
        super.onPause()
        mSpHelper.saveDefaultItemId(viewPager.currentItem)
    }

    override fun initLayoutId(): Int = R.layout.fe_fragment_file_viewer

    override fun onBackPressed(): Boolean {
        return mAdapter.getCurrentFragment(viewPager.currentItem)?.onBackPressed()!!
    }

}