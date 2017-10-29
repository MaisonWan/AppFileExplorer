package com.domker.app.explorer.fragment

import android.app.Fragment

/**
 * Created by wanlipeng on 2017/9/11.
 */
object FragmentCache {
    private val mCache = HashMap<Class<*>, Fragment>()

    /**
     * 从缓存中获取
     */
    fun getFragment(clazz: Class<*>): Fragment {
        if (mCache.containsKey(clazz)) {
            return mCache[clazz]!!
        }
        val f = clazz.newInstance() as Fragment
        mCache[clazz] = f
        return f
    }
}