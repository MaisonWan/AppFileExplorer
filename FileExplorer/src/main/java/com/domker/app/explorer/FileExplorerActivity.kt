package com.domker.app.explorer

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.domker.app.explorer.fragment.AppInfoFragment
import com.domker.app.explorer.fragment.FileListFragment
import com.domker.app.explorer.fragment.PhoneInfoFragment
import com.domker.app.explorer.helper.KeyPressHelper
import com.domker.app.explorer.helper.SQLHelper
import com.domker.app.explorer.hostapp.HostApp

class FileExplorerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mToolbar: Toolbar
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavView: NavigationView
    private lateinit var mAppIcon: ImageView
    private lateinit var mAppName: TextView
    private lateinit var mPackageName: TextView

    private lateinit var mBackPressHelper: KeyPressHelper

    private var exitTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fe_activity_file_explorer)
        initViews()
        setSupportActionBar(mToolbar)
        initListener()
        loadFragment(FileListFragment())
    }

    private fun initListener() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "测试按钮，没有功能", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.fe_navigation_drawer_open,
                R.string.fe_navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavView.setNavigationItemSelectedListener(this)
        mBackPressHelper = KeyPressHelper()
    }

    private fun initViews() {
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 抽屉打开的时候优先关闭
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        if (mBackPressHelper.onBackPressed(keyCode, event)) {
            return false
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
                loadFragment(AppInfoFragment())
            }
            R.id.nav_file_explorer -> {
                loadFragment(FileListFragment())
            }
            R.id.nav_phone_info -> {
                loadFragment(PhoneInfoFragment())
            }
            R.id.nav_manage -> {

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

    private fun loadFragment(fragment: Fragment) {
        val manager = fragmentManager.beginTransaction()
        manager.replace(R.id.fragment_content, fragment)
        manager.commitAllowingStateLoss()
    }
}
