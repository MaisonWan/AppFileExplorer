package com.domker.db.sqlviewer.listener

import android.view.View

/**
 * Created by Maison on 2017/7/10.
 */

interface OnItemClickLitener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int): Boolean
}