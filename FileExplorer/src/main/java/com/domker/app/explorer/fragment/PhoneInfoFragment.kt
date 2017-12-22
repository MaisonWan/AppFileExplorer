package com.domker.app.explorer.fragment

import android.Manifest
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ArrayAdapter
import com.domker.app.explorer.R
import com.domker.app.explorer.helper.PermissionHelper
import com.domker.app.explorer.util.DrawableUtils
import com.domker.app.explorer.util.PhoneInfo
import com.domker.app.explorer.util.hideMenu
import kotlinx.android.synthetic.main.fe_phone_info_layout.*


/**
 * 手机信息
 * @author wanlipeng
 */
class PhoneInfoFragment : BaseFragment() {
    private lateinit var mAdapter: ArrayAdapter<String>
    private val mData = ArrayList<String>()
    private lateinit var permissionHelper: PermissionHelper

    override fun init(context: Context, view: View) {
        activity.title = getString(R.string.fe_title_phone_info)
        mAdapter = ArrayAdapter(context, R.layout.fe_phone_info_item, mData)
        permissionHelper = PermissionHelper(context)
    }

    override fun initAssistButtonDrawable(context: Context): Drawable? {
        return DrawableUtils.getDrawable(context, R.drawable.fe_ic_refresh_black)
    }

    override fun onAssistButtonClick(view: View) {
        initData(activity)
        mAdapter.notifyDataSetChanged()
        listView.smoothScrollToPosition(0)
    }

    override fun onShown(context: Context) {
        if (permissionHelper.check(Manifest.permission.READ_PHONE_STATE)) {
            initData(context)
        }
        listView.adapter = mAdapter
    }

    override fun initLayoutId(): Int = R.layout.fe_phone_info_layout

    override fun onBackPressed(): Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.hideMenu()
    }
    
    /**
     * 初始化设备信息
     */
    private fun initData(context: Context) {
        val util = PhoneInfo(context)

        mData.clear()
        val totalMemory = util.getTotalMemory()
        val showTotalMemory = String.format("Total Memory : %s", totalMemory)
        mData.add(showTotalMemory)

        val availMemory = util.getAvailMemory()
        val showAvailMemory = String.format("Avail Memory : %s", availMemory)
        mData.add(showAvailMemory)

        val maxHeap = util.getAppMaxHeapSize()
        val appMaxHeap = String.format("App Max Heap Size : %dKB(%.2fMB)", maxHeap, maxHeap / 1024.0f)
        mData.add(appMaxHeap)

        val totalHeap = util.getAppTotalHeapSize()
        val appTotalHeap = String.format("App Total Heap Size : %dKB(%.2fMB)", totalHeap, totalHeap / 1024.0f)
        mData.add(appTotalHeap)

        val freeHeap = util.getAppFreeHeapSize()
        val appFreeHeap = String.format("App Free Heap Size : %dKB(%.2fMB)", freeHeap, freeHeap / 1024.0f)
        mData.add(appFreeHeap)

        val sdkVersion = String.format("SDK Version : %s", util.getSdkVersion())
        mData.add(sdkVersion)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 6.0以上新增，查看双卡信息
            mData.add(String.format("IMEI-1 : %s", util.getImei(0)))
            mData.add(String.format("IMEI-2 : %s", util.getImei(1)))
        } else {
            mData.add(String.format("IMEI : %s", util.getImei()))
        }

        val imsi = String.format("IMSI : %s", util.getImsi())
        mData.add(imsi)

        val androidId = String.format("Android ID : %s", util.getAndroidID())
        mData.add(androidId)

        val macAddress = String.format("Mac Address : %s", util.getMacAddress())
        mData.add(macAddress)

        mData.add("Screen Size : " + util.getScreenSize(activity))
        mData.add("Scaled Density : " + util.getDpiDensity())
        mData.add("Density DPI : " + util.getDensityDpi())
        mData.add("Density: " + util.getDensity())
        mData.addAll(util.getOsBuildInfo())

    }
}
