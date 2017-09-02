package com.domker.app.explorer.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * 用于RecyclerView中Item的布局
 * Created by wanlipeng on 2017/9/2.
 */
class ItemDivider : RecyclerView.ItemDecoration() {

    /**
     *
     * @param outRect 边界
     * @param view recyclerView ItemView
     * @param parent recyclerView
     * @param state recycler 内部数据管理
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        //设定底部边距为1px
        outRect.set(0, 1, 0, 1)
    }
}
