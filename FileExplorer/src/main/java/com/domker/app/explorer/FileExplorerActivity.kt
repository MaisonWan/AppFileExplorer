package com.domker.app.explorer

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.domker.app.explorer.fragment.*
import com.domker.app.explorer.helper.KeyPressHelper
import com.domker.app.explorer.helper.SQLHelper
import com.domker.app.explorer.hostapp.HostApp

/**
 * 查看文件和app信息，机型信息的总入口
 */
class FileExplorerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mFab: FloatingActionButton
    private lateinit var mToolbar: Toolbar
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavView: NavigationView
    private lateinit var mAppIcon: ImageView
    private lateinit var mAppName: TextView
    private lateinit var mPackageName: TextView
    private lateinit var mBackPressHelper: KeyPressHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fe_activity_file_explorer)
        initViews()
        setSupportActionBar(mToolbar)
        initListener()
        loadFragment(FileViewerFragment::class.java)
    }

    private fun initListener() {
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.fe_navigation_drawer_open,
                R.string.fe_navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavView.setNavigationItemSelectedListener(this)
        mBackPressHelper = KeyPressHelper(this)
        mFab.setOnClickListener { view ->
            val currentFragment = fragmentManager.findFragmentById(R.id.fragment_content) as IActionFragment
            currentFragment?.onAssistButtonClick(view)
        }
    }

    private fun initViews() {
        mFab = findViewById(R.id.fab)
        mToolbar = findViewById(R.id.toolbar)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavView = findViewById(R.id.nav_view)
        val headerView = mNavView.getHeaderView(0)

        mAppIcon = headerView.findViewById(R.id.imageViewIcon)
        mAppName = headerView.findViewById(R.id.textViewAppName)
        mPackageName = headerView.findViewById(R.id.textViewPackage)

        mAppIcon.setImageDrawable(HostApp.getHostAppIcon(this))
        mAppName.text = HostApp.getHostAppName(baseContext)
        mPackageName.text = HostApp.getHostAppPackage(baseContext)
    }

    private fun initAssistButton(fragment: IActionFragment) {
        val drawable = fragment.initAssistButtonDrawable(this)
        if (drawable != null) {
            mFab.visibility = View.VISIBLE
            mFab.setImageDrawable(drawable)
        } else {
            mFab.visibility = View.GONE
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 抽屉打开的时候优先关闭
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        if (mBackPressHelper.onKeyPressed(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_app_info -> {
                loadFragment(AppInfoFragment::class.java)
            }
            R.id.nav_file_explorer -> {
                loadFragment(FileViewerFragment::class.java)
            }
            R.id.nav_phone_info -> {
                loadFragment(PhoneInfoFragment::class.java)
            }
            R.id.nav_settings -> {
                loadFragment(SettingsFragment::class.java)
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun testCreateDatabase() {
        (0..5).map { SQLHelper(this, "test_$it.db", null, 1) }
                .forEach { it.readableDatabase.close() }
    }

    private fun loadFragment(clazz: Class<*>) {
        val fragment = FragmentCache.getFragment(clazz)
        val manager = fragmentManager.beginTransaction()
        manager.replace(R.id.fragment_content, fragment)
        manager.commitAllowingStateLoss()
        // 初始化按钮的显示
        initAssistButton(fragment as IActionFragment)
    }
}
