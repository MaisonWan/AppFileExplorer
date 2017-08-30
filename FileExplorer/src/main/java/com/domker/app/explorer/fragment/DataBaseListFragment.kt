package com.domker.app.explorer.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.domker.app.explorer.R
import com.domker.app.explorer.adapter.DataBaseListAdapter
import com.domker.app.explorer.file.FileManager
import com.domker.app.explorer.helper.DbManager
import com.domker.app.explorer.listener.OnItemClickListener

/**
 * Created by Maison on 2017/7/9.
 */
class DataBaseListFragment : BaseFragment() {

    private lateinit var dbRecyclerView: RecyclerView
    private lateinit var fileManager: FileManager

    override fun init(context: Context, view: View) {
        activity.title = "文件浏览"
        dbRecyclerView = view.findViewById(R.id.dbRecyclerView)
        fileManager = FileManager()
    }

    override fun onShown(context: Context) {
        loadDataFiles()
    }

    override fun initLayoutId(): Int {
        return R.layout.fragment_database_list
    }

    private fun loadDataFiles() {
//        val files = DbManager.getDataFiles(activity)
//                .filter { it.name.endsWith(".db") }
//        val names = ArrayList<String>()
//        files.mapTo(names) { it.name }
        val files = fileManager.getFileList(activity.filesDir.parent)
        val names = ArrayList<String>()
        files.mapTo(names) { it.file.name }
        val adapter = DataBaseListAdapter(activity, names)
        adapter.itemClickListener = object: OnItemClickListener {
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