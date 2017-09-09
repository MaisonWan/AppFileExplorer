package com.domker.app.explorer.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build

/**
 * Created by wanlipeng on 2017/9/5.
 */
object DrawableUtils {

    /**
     * 获取drawable
     */
    fun getDrawable(context: Context, resId: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= 21) {
            context.getDrawable(resId)
        } else {
            context.resources.getDrawable(resId)
        }
    }
}