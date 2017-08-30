package com.domker.app.explorerdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.domker.app.explorer.FileExplorerActivity

/**
 * Created by Maison on 2017/8/31.
 */
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, FileExplorerActivity::class.java)
        startActivity(intent)
    }

}