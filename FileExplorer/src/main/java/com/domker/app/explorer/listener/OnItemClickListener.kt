package com.domker.app.explorer.listener

import android.view.View

/**
 * Created by Maison on 2017/7/10.
 */

interface OnItemClickListener {

    fun onItemClick(view: View, position: Int)

    fun onItemLongClick(view: View, position: Int): Boolean
}