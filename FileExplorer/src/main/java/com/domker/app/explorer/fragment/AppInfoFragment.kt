package com.domker.app.explorer.fragment

import android.content.Context
import android.view.View
import com.domker.app.explorer.R

/**
 * App信息
 * Created by wanlipeng on 2017/9/1.
 */
class AppInfoFragment : BaseFragment() {

    override fun init(context: Context, view: View) {
        activity.title = getString(R.string.fe_title_app_info)
    }

    override fun onShown(context: Context) {
    }

    override fun initLayoutId(): Int = R.layout.fe_app_info_layout


}