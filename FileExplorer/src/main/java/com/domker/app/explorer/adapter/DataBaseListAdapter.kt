package com.domker.app.explorer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.domker.app.explorer.R
import com.domker.app.explorer.listener.OnItemClickListener

/**
 * Created by Maison on 2017/7/10.
 */
class DataBaseListAdapter(private val context: Context, val data: List<String>) : RecyclerView.Adapter<DataBaseViewHolder>() {
    var itemClickListener: OnItemClickListener? = null
        set(value) {
            field = value
        }

    override fun onBindViewHolder(holder: DataBaseViewHolder?, position: Int) {
        val name = data[position]
        if (holder != null) {
            holder.nameTextView.text = name
            holder.itemView.setOnClickListener {
                itemClickListener?.onItemClick(holder.itemView, position)
            }
            holder.itemView.setOnLongClickListener {
                itemClickListener?.onItemLongClick(holder.itemView, position) ?: false
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
        return DataBaseViewHolder(view)
    }

}

class DataBaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(R.id.nameTextView)
}