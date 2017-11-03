package com.domker.app.explorer.util

import android.view.Menu

/**
 * 扩展方法
 * Created by Maison on 2017/9/11.
 */


/**
 * 隐藏菜单显示
 */
fun Menu.hideMenu() {
    (0 until this.size()).forEach { this.getItem(it).isVisible = false }
}
