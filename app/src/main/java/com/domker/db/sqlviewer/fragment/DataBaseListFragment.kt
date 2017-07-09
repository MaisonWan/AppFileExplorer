package com.domker.db.sqlviewer.fragment

import android.content.Context
import android.widget.ArrayAdapter
import com.domker.db.sqlviewer.R
import com.domker.db.sqlviewer.helper.DbManager
import kotlinx.android.synthetic.main.fragment_database_list.*

/**
 * Created by Maison on 2017/7/9.
 */
class DataBaseListFragment : BaseFragment() {

    override fun init(context: Context) {
        loadDataFiles()
    }

    override fun initLayoutId(): Int {
        return R.layout.fragment_database_list
    }

    fun loadDataFiles() {
        val files = DbManager.getDataFiles(activity)
                .filter { it.name.endsWith(".db") }
        val names = ArrayList<String>()
        files.mapTo(names) { it.name }
        val adapter = ArrayAdapter<String>(activity, R.layout.list_item_layout, names)
        dbListView.adapter = adapter
    }
}