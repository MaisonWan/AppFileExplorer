package com.domker.app.explorer.fragment

import android.content.Context
import android.view.View

/**
 * Created by wanlipeng on 2017/9/4.
 */
interface IActionFragment {
    /**
     * 初始化回调
     * @param context
     */
    fun init(context: Context, view: View)

    fun onShown(context: Context)

    /**
     * 回调布局
     */
    fun initLayoutId(): Int

    /**
     * 按下back按键
     */
    fun onBackPressed(): Boolean
}