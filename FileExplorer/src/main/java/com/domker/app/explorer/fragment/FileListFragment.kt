package com.domker.app.explorer.fragment

import android.Manifest
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.domker.app.explorer.R
import com.domker.app.explorer.adapter.FileListAdapter
import com.domker.app.explorer.adapter.ItemDivider
import com.domker.app.explorer.file.FileInfo
import com.domker.app.explorer.file.FileLoader
import com.domker.app.explorer.file.FileOpen
import com.domker.app.explorer.helper.PermissionHelper
import com.domker.app.explorer.helper.SharedPreferencesHelper
import com.domker.app.explorer.listener.OnItemClickListener
import java.io.File

/**
 * Created by Maison on 2017/7/9.
 */
class FileListFragment : BaseFragment() {
    private lateinit var mTextViewPath: TextView
    private lateinit var mRecyclerViewFileList: RecyclerView
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: FileListAdapter
    private lateinit var mFileLoader: FileLoader
    private lateinit var mSpHepler: SharedPreferencesHelper
    private var mCurrentPath: String? = null

    // 完成接收的回调
    private val mCallback = object : FileLoader.FileLoaderCallback {
        override fun onReceiveResult(result: List<FileInfo>) {
            mAdapter.setFileList(result)
            mAdapter.notifyDataSetChanged()
            // 还原位置
            val position = mAdapter.getRecordPosition(mCurrentPath!!)
            mLayoutManager.scrollToPosition(position)
        }
    }


    override fun init(context: Context, view: View) {
        activity.title = "文件浏览"
        mRecyclerViewFileList = view.findViewById(R.id.recyclerViewFiles)
        mTextViewPath = view.findViewById(R.id.textViewPath)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerViewFileList.layoutManager = mLayoutManager
        mSpHepler = SharedPreferencesHelper(activity)
        initAdapter()
    }

    override fun onShown(context: Context) {
        if (PermissionHelper(activity).check(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            loadPathFiles(mSpHepler.getDefaultPath())
        }
    }

    override fun initLayoutId() = R.layout.fe_fragment_database_list

    override fun onBackPressed(): Boolean {
        if (mCurrentPath == "/") {
            return false
        }
        loadPathFiles(File(mCurrentPath).parent)
        return true
    }

    override fun onStop() {
        super.onStop()
        mSpHepler.saveDefaultPath(mCurrentPath!!)
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        mAdapter = FileListAdapter(activity)
        mRecyclerViewFileList.adapter = mAdapter
        mRecyclerViewFileList.addItemDecoration(ItemDivider())
        mAdapter.itemClickListener = object: OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val fileInfo = mAdapter.getFileInfoItem(position)
                if (fileInfo.isFile()) {
                    // 如果是文件，则打开
                    FileOpen.openFile(activity, fileInfo)
                } else {
                    loadPathFiles(fileInfo.file.absolutePath)
                }
            }

            override fun onItemLongClick(view: View, position: Int): Boolean {
                Toast.makeText(activity, "On Long Click Item $position", Toast.LENGTH_SHORT).show()
                return true
            }
        }
    }

    /**
     * 加载指定路径下的文件
     */
    private fun loadPathFiles(path: String) {
        // 跳转之前，记录位置
        if (mCurrentPath != null) {
            mAdapter.recordPosition(mCurrentPath!!, mLayoutManager)
        }
        mCurrentPath = path
        mTextViewPath.text = path
        mFileLoader = FileLoader(mCallback)
        mFileLoader.execute(path)
    }
}