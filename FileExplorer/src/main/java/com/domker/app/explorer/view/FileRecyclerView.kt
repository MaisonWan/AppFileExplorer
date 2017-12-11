package com.domker.app.explorer.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView

/**
 * Created by Maison on 2017/11/18.
 */
class FileRecyclerView: RecyclerView {

    private lateinit var contextMenuInfo: AdapterView.AdapterContextMenuInfo
    var selectPosition: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun showContextMenuForChild(originalView: View):Boolean {
        selectPosition = getChildAdapterPosition(originalView)
        val longId = getChildItemId(originalView)
        contextMenuInfo = AdapterView.AdapterContextMenuInfo(originalView, selectPosition, longId)
        return super.showContextMenuForChild(originalView)
    }

    override fun getContextMenuInfo(): ContextMenu.ContextMenuInfo = contextMenuInfo

}