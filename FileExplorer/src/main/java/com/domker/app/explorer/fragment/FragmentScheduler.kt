package com.domker.app.explorer.fragment

import android.app.Fragment
import android.app.FragmentManager
import com.domker.app.explorer.R

/**
 * Fragment调度
 * Created by wanlipeng on 2017/9/12.
 */
class FragmentScheduler(private val fragmentManager: FragmentManager) {
    /**
     * 当前正在显示的fragment
     */
    private var mCurrentFragment: Fragment? = null

    /**
     * 加载并显示执行类型的Fragment
     */
    fun loadFragment(clazz: Class<*>) : Fragment {
        val fragment = FragmentCache.getFragment(clazz)
        val manager = fragmentManager.beginTransaction()
        if (mCurrentFragment != null) {
            manager.hide(mCurrentFragment)
        }
        val frg = fragmentManager.findFragmentByTag(makeFragmentTag(fragment))
        if (frg == null) {
            manager.add(R.id.fragment_content, fragment, makeFragmentTag(fragment))
        } else {
            manager.show(frg)
        }
        manager.commitAllowingStateLoss()
        mCurrentFragment = fragment
        return fragment
    }

    /**
     * 生成fragment的tag
     */
    private fun makeFragmentTag(fragment: Fragment) : String {
        return fragment::class.java.name + ":" + fragment.hashCode()
    }
}