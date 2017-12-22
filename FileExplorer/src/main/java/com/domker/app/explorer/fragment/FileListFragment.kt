package com.domker.app.explorer.fragment

import android.Manifest
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.domker.app.explorer.R
import com.domker.app.explorer.adapter.FileListAdapter
import com.domker.app.explorer.adapter.ItemDivider
import com.domker.app.explorer.file.FileInfo
import com.domker.app.explorer.file.FileLoader
import com.domker.app.explorer.file.FileOpen
import com.domker.app.explorer.helper.DbManager
import com.domker.app.explorer.helper.MenuHelper
import com.domker.app.explorer.helper.PermissionHelper
import com.domker.app.explorer.helper.SharedPreferencesHelper
import com.domker.app.explorer.listener.OnItemClickListener
import com.domker.app.explorer.util.DrawableUtils
import com.domker.app.explorer.util.PhoneInfo
import com.domker.app.explorer.view.FileRecyclerView
import kotlinx.android.synthetic.main.fe_fragment_file_list.*
import java.io.File

/**
 * 文件浏览的具体显示界面
 * Created by Maison on 2017/7/9.
 */
class FileListFragment : BaseFragment() {
    companion object {
        val KEY_DEFAULT_PATH = "default_path"
        val KEY_IS_FAVORITE = "default_favorite"
    }

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: FileListAdapter
    private lateinit var mFileLoader: FileLoader
    private lateinit var mDbManager: DbManager
    private lateinit var mSpHelper: SharedPreferencesHelper
    private var mCurrentPath: String? = null

    private lateinit var mDefaultPath: String
    private var isShowFavorite = false

    // 完成接收的回调
    private val mCallback = object : FileLoader.FileLoaderCallback {
        override fun onReceiveResult(result: List<FileInfo>) {
            mAdapter.setFileList(result)
            mAdapter.notifyDataSetChanged()
            // 还原位置
            val position = mAdapter.getRecordPosition(mCurrentPath)
            mLayoutManager.scrollToPosition(position)
        }
    }

    override fun init(context: Context, view: View) {
        activity.title = "文件浏览"
        mSpHelper = SharedPreferencesHelper.getInstance(activity)
        mDbManager = DbManager(activity)
        initViews()
        initArguments()
        initAdapter()
        // 显示上次记录的目录
        if (PermissionHelper(activity).check(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (isShowFavorite) {
                loadFavoritePaths()
            } else {
                loadPathFiles(mSpHelper.getDefaultPath(mDefaultPath))
            }
        }
    }

    private fun initViews() {
        mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.isItemPrefetchEnabled = true
        recyclerViewFiles.layoutManager = mLayoutManager
        registerForContextMenu(recyclerViewFiles)
    }

    override fun initAssistButtonDrawable(context: Context): Drawable? {
        return DrawableUtils.getDrawable(context, R.drawable.fe_ic_arrow_upward_black)
    }

    override fun onAssistButtonClick(view: View) {
        recyclerViewFiles.smoothScrollToPosition(0)
    }

    override fun onShown(context: Context) {

    }

    override fun initLayoutId() = R.layout.fe_fragment_file_list

    override fun onBackPressed(): Boolean {
        if (mCurrentPath == mDefaultPath) {
            return false
        }
        loadPathFiles(File(mCurrentPath).parent)
        return true
    }

    override fun onStop() {
        super.onStop()
        if (settings.isRecordLastPath() && !mCurrentPath.isNullOrEmpty()) {
            mSpHelper.saveDefaultPath(mCurrentPath!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater?.inflate(R.menu.fe_filelist_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.fe_menu_main -> loadPathFiles(mDefaultPath)
            R.id.fe_menu_reload -> reload()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v is FileRecyclerView) {
            val fileInfo = mAdapter.getFileInfoItem(v.selectPosition)
            // 跳转上层目录不弹出菜单
            if (!fileInfo.isJumpParentPath) {
                MenuHelper.createFileListMenu(menu, fileInfo)
            }
        }
        println(v)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            MenuHelper.MENU_FAVORITE -> {
                println("menu_favorite ${item?.itemId}")
                return true
            }
            MenuHelper.MENU_DETAIL_INFO -> {
                println("menu_detail_info ${item?.itemId}")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun initArguments() {
        isShowFavorite = arguments.getBoolean(KEY_IS_FAVORITE, false)
        // 获取传递过程中的初始化路径
        mDefaultPath = arguments.getString(KEY_DEFAULT_PATH, PhoneInfo.getSdCardPath()!!)
    }

    /**
     * 初始化适配器
     */
    private fun initAdapter() {
        mAdapter = FileListAdapter(activity)
        recyclerViewFiles.adapter = mAdapter
        recyclerViewFiles.addItemDecoration(ItemDivider())
        mAdapter.itemClickListener = object: OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val fileInfo = mAdapter.getFileInfoItem(position)
                when {
                    fileInfo.isJumpParentPath -> loadPathFiles(File(mCurrentPath).parent)
                    fileInfo.isFile() -> // 如果是文件，则打开
                        FileOpen.openFile(activity, fileInfo)
                    else -> loadPathFiles(fileInfo.file.absolutePath)
                }
            }

            override fun onItemLongClick(view: View, position: Int): Boolean {
                return false
            }
        }
    }

    /**
     * 加载指定路径下的文件
     */
    private fun loadPathFiles(path: String) {
        // 跳转之前，记录位置
        if (mCurrentPath != null && path != mCurrentPath) {
            mAdapter.recordPosition(mCurrentPath!!, mLayoutManager)
        }
        mCurrentPath = path
        textViewPath.text = path
        mFileLoader = FileLoader(mCallback)
        mFileLoader.fileSortType = settings.getFileSortType()
        mFileLoader.rootPath = mDefaultPath
        mFileLoader.execute(path)
    }

    private fun loadFavoritePaths() {
        var paths = mDbManager.getAllPathFavorite()
        mAdapter.setFileList(paths)
        mAdapter.notifyDataSetChanged()
    }

    /**
     * 重新载入
     */
    private fun reload() {
        if (isShowFavorite) {
            loadFavoritePaths()
        } else if (mCurrentPath != null) {
            loadPathFiles(mCurrentPath!!)
        }
    }
}