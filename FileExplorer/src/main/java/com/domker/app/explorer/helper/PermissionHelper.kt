package com.domker.app.explorer.helper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat


/**
 * Created by wanlipeng on 2017/9/2.
 */
class PermissionHelper(val context: Context) {

    /**
     * 是否检测权限
     */
    fun check(permission: String): Boolean {
        return if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permission)
            false
        } else true
    }

    private fun requestPermissions(permission: String) {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
//        }
        ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 1)
    }
}