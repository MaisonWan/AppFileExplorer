package com.domker.db.sqlviewer.fragment

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Fragment的基类
 *
 * Created by Maison on 2017/7/9.
 */

open abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(initLayoutId(), null)
        init(activity)
        return view
    }

    override fun onResume() {
        super.onResume()
        onShown(activity)
    }


    /**
     * 初始化回调
     * @param context
     */
    abstract fun init(context: Context)

    abstract fun onShown(context: Context)

    /**
     * 回调布局
     */
    abstract fun initLayoutId(): Int
}