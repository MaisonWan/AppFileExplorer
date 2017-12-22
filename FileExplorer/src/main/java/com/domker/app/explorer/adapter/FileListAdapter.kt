package com.domker.app.explorer.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.domker.app.explorer.R
import com.domker.app.explorer.file.FileInfo
import com.domker.app.explorer.helper.IconLruCache
import com.domker.app.explorer.listener.OnItemClickListener

/**
 * 显示文件列表的适配器
 * Created by Maison on 2017/7/10.
 */
class FileListAdapter(val context: Context) : RecyclerView.Adapter<FileInfoViewHolder>() {
    private val fileInfoList: ArrayList<FileInfo> = ArrayList()
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val mIconCache: IconLruCache = IconLruCache(context)

    private val mPositionMap: MutableMap<String, Int> = HashMap()

    var itemClickListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: FileInfoViewHolder?, position: Int) {
        val info = fileInfoList[position]
        if (holder != null) {
            holder.textViewName.text = info.fileName
            if (info.isFile()) {
                holder.textViewDate.text = info.getFileDate() + "  " + info.getFileSize(context)
                holder.imageViewDirection.visibility = View.GONE
            } else {
                holder.textViewDate.text = info.getFileDate()
                holder.imageViewDirection.visibility = View.VISIBLE
            }
            holder.imageViewFileType.setImageDrawable(mIconCache.getDrawable(info))
            holder.itemView.setOnClickListener {
                itemClickListener?.onItemClick(holder.itemView, position)
            }
            holder.itemView.setOnLongClickListener {
                itemClickListener?.onItemLongClick(holder.itemView, position) ?: false
            }
        }
    }

    override fun getItemCount() = fileInfoList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FileInfoViewHolder {
        val view = mLayoutInflater.inflate(R.layout.fe_list_item_layout, parent, false)
        return FileInfoViewHolder(view)
    }

    /**
     * 设置当前显示文件信息
     */
    fun setFileList(files: List<FileInfo>) {
        fileInfoList.clear()
        fileInfoList.addAll(files)
    }

    /**
     * 得到指定位置的文件信息
     */
    fun getFileInfoItem(position: Int): FileInfo {
        return fileInfoList[position]
    }

    /**
     * 记录当前滑动的位置
     */
    fun recordPosition(path: String, layoutManager: LinearLayoutManager) {
        val position = layoutManager.findFirstCompletelyVisibleItemPosition()
        mPositionMap.put(path, position)
    }

    /**
     * 获取当前存储的位置
     */
    fun getRecordPosition(path: String?): Int {
        return if (path.isNullOrEmpty()) 0 else mPositionMap[path] ?: 0
    }
}

class FileInfoViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

    val textViewName: TextView = view.findViewById(R.id.textViewName)
    val textViewDate: TextView = view.findViewById(R.id.textViewDate)
    val imageViewFileType: ImageView = view.findViewById(R.id.imageViewFileType)
    val imageViewDirection: ImageView = view.findViewById(R.id.imageViewDirection)

    init {
        view.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {

    }
}