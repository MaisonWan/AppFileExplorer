package com.domker.db.sqlviewer.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import com.domker.app.explorer.fragment.BaseFragment
import com.domker.app.explorer.helper.DbManager
import com.domker.db.explorer.R

/**
 * Created by Maison on 2017/7/9.
 */
class DataBaseListFragment : BaseFragment() {
    override fun init(context: Context, view: View) {
        activity.title = "DataBase List"
    }

    override fun initAssistButtonDrawable(context: Context): Drawable? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAssistButtonClick(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShown(context: Context) {
        loadDataFiles()
    }

    override fun initLayoutId(): Int {
        return R.layout.fe_fragment_file_list;
    }

    fun loadDataFiles() {
        val files = DbManager.getDataFiles(activity).filter { it.name.endsWith(".db") }
        val names = ArrayList<String>()
        files.mapTo(names) { it.name }
//        val adapter = DataBaseListAdapter(activity, names)
//        adapter.itemClickListener = object: OnItemClickLitener {
//            override fun onItemClick(view: View, position: Int) {
//                Toast.makeText(activity, "On Click Item $position", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onItemLongClick(view: View, position: Int): Boolean {
//                Toast.makeText(activity, "On Long Click Item $position", Toast.LENGTH_SHORT).show()
//                return true
//            }
//
//        }
//        dbRecyclerView.layoutManager = LinearLayoutManager(activity)
//        dbRecyclerView.adapter = adapter
    }
}