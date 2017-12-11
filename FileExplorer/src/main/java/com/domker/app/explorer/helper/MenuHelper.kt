package com.domker.app.explorer.helper

import android.view.ContextMenu
import com.domker.app.explorer.file.FileInfo

/**
 * Created by Maison on 2017/11/19.
 */
object MenuHelper {
    val MENU_FAVORITE = 1
    val MENU_DETAIL_INFO = 2

    /**
     * 创建文件列表的菜单
     */
    fun createFileListMenu(menu: ContextMenu?, fileInfo: FileInfo) {
        if (fileInfo.isFile()) {
            menu?.add(1, MENU_DETAIL_INFO,1,"详细信息")
        } else {
            menu?.add(1, MENU_FAVORITE,1,"收藏")
        }
    }
}