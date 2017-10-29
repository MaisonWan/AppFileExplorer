package com.domker.app.explorer.fragment

import android.content.Context
import android.graphics.drawable.Drawable
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

    /**
     * 初始化浮动功能按钮的图像，返回null，不显示
     */
    fun initAssistButtonDrawable(context: Context): Drawable?

    /**
     * 浮动功能按钮点击的回调
     */
    fun onAssistButtonClick(view: View)

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