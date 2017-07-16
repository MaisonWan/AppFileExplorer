package com.domker.db.sqlviewer.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.domker.db.annotation.FragmentShard
import com.domker.db.sqlviewer.R
import com.domker.db.sqlviewer.adapter.DataBaseListAdapter
import com.domker.db.sqlviewer.helper.DbManager
import com.domker.db.sqlviewer.listener.OnItemClickLitener
import kotlinx.android.synthetic.main.fragment_database_list.*

/**
 * Created by Maison on 2017/7/9.
 */
@FragmentShard(FragmentType.UNIT_TYPE_DATA_LIST)
class DataBaseListFragment : BaseFragment() {
    override fun init(context: Context) {
        activity.title = "DataBase List"
    }

    override fun onShown(context: Context) {
        loadDataFiles()
    }

    override fun initLayoutId(): Int {
        return R.layout.fragment_database_list
    }

    fun loadDataFiles() {
        val files = DbManager.getDataFiles(activity).filter { it.name.endsWith(".db") }
        val names = ArrayList<String>()
        files.mapTo(names) { it.name }
        val adapter = DataBaseListAdapter(activity, names)
        adapter.itemClickListener = object: OnItemClickLitener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(activity, "On Click Item $position", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View, position: Int): Boolean {
                Toast.makeText(activity, "On Long Click Item $position", Toast.LENGTH_SHORT).show()
                return true
            }

        }
        dbRecyclerView.layoutManager = LinearLayoutManager(activity)
        dbRecyclerView.adapter = adapter
    }
}