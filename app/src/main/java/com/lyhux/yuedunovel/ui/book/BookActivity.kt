package com.lyhux.yuedunovel.ui.book

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.lyhux.yuedunovel.R

class BookActivity : AppCompatActivity() {

    private var lastBackTime = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_book_detail_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.setGraph(navController.graph,
                BookFragmentArgs(intent.getStringExtra("book_id") ?: "").toBundle())
        //
        // navController.navInflater
    }
    // @SuppressLint("ResourceType")
    // override fun finish() {
        // val curTime = System.currentTimeMillis()
        // if (curTime - lastBackTime > 2000) {
        //     lastBackTime = curTime
        //     val typeValue = TypedValue().apply {
        //         ActivityUtils.getTopActivity()
        //                 .theme.resolveAttribute(R.attr.colorPrimary, this, true)
        //     }
        //
        //     SnackbarUtils.with(window.decorView)
        //             .setMessage("再次返回退出")
        //             .setBgResource(R.color.colorPrimary)
        //             // .setMessageColor()
        //             .show()
        // } else {
        //     super.finish()
        // }
    // }
}