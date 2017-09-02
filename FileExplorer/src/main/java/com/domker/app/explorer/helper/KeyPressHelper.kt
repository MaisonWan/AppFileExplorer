package com.domker.app.explorer.helper

import android.app.Activity
import android.app.FragmentManager
import android.view.KeyEvent
import android.widget.Toast
import com.domker.app.explorer.R
import com.domker.app.explorer.fragment.BaseFragment

/**
 * Created by wanlipeng on 2017/9/2.
 */
class KeyPressHelper(val activity: Activity) {
    private val fragmentManager: FragmentManager = activity.fragmentManager
    private var exitTime: Long = 0

    /**
     * 返回false，则不处理
     */
    fun onKeyPressed(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event!!.repeatCount == 0) {
            // 优先处理fragment的需求
            if (onFragmentBackPressed()) {
                return true
            }
            return if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(activity, R.string.fe_back_toast, Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
                true
            } else {
                activity.finish()
                false
            }
        }
        return false
    }

    /**
     * 调用当前fragment中，如何处理返回按键
     */
    private fun onFragmentBackPressed(): Boolean {
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_content) as BaseFragment
        return currentFragment.onBackPressed()
    }
}