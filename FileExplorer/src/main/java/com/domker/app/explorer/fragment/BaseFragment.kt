package com.domker.app.explorer.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domker.app.explorer.helper.SettingsHelper

/**
 * Fragment的基类
 *
 * Created by Maison on 2017/7/9.
 */

open abstract class BaseFragment : Fragment(), IActionFragment {
    protected lateinit var settings: SettingsHelper

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater!!.inflate(initLayoutId(), null)
        settings = SettingsHelper(activity)
        init(activity, view)
        setHasOptionsMenu(true)
        return view
    }

    override fun onResume() {
        super.onResume()
        onShown(activity)
    }

}