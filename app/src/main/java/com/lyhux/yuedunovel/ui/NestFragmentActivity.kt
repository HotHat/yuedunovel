package com.lyhux.yuedunovel.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.SnackbarUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.ui.account.AccountFragment
import com.lyhux.yuedunovel.ui.bookshelf.BookshelfFragment
import com.lyhux.yuedunovel.ui.library.LibraryFragment


class NestFragmentActivity : AppCompatActivity() {

    private var lastBackTime = 0L

    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var currentFragment: Fragment
    private lateinit var deleteBar: LinearLayout
    private lateinit var navBar: BottomNavigationView
    private lateinit var selectAllBtn: RadioButton
    private  var isSelectAll: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nest_fragment)

        // 初始化
        deleteBar = findViewById(R.id.nest_frag_sel)
        navBar = findViewById(R.id.bottom_navigation)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        supportActionBar?.setDisplayShowTitleEnabled(false);

        fragmentList = arrayListOf<Fragment>(
            BookshelfFragment.newInstance(),
            LibraryFragment.newInstance(),
            AccountFragment.newInstance()
        )

        for ((index, f) in fragmentList.withIndex()) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, f, "fragment${index}").hide(f).commit()
        }

        supportFragmentManager.beginTransaction()
            //.add(R.id.fragment_container, fragmentList[0])
            .show(fragmentList[0])
            .commit()
        currentFragment = fragmentList[0]

        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.btn_nag_bookshelf-> {
                    supportFragmentManager.beginTransaction()
                        .hide(currentFragment)
                        .show(fragmentList[0])
                        .commit()
                    currentFragment = fragmentList[0]

                }
                R.id.btn_nag_library->  {
                    supportFragmentManager.beginTransaction()
                        .hide(currentFragment)
                        .show(fragmentList[1])
                        .commit()

                    currentFragment = fragmentList[1]
                }
                R.id.btn_nag_account->  {
                    supportFragmentManager.beginTransaction()
                        .hide(currentFragment)
                        .show(fragmentList[2])
                        .commit()
                    currentFragment = fragmentList[2]
                }
                else -> { println("hello, 4");  }
            }

            true

        }

        selectAllBtn = deleteBar.findViewById<RadioButton>(R.id.act_nest_select)
        val delBtn = deleteBar.findViewById<Button>(R.id.act_nest_del)
        selectAllBtn.setOnClickListener {
            Log.e(TAG, "select all radio button click")
            val itemId = bottomNavigationView.selectedItemId

            if (itemId == R.id.btn_nag_bookshelf) {
                Log.e(TAG, "select btn nag bookshelf" )
            }

            Log.e(TAG, "item id: $itemId" )
            val firstFrag = fragmentList[0] as NestFragmentActivity.Editable
            isSelectAll = !isSelectAll

            if (isSelectAll) {
                firstFrag.selectAll()
            } else {
                firstFrag.cleanAll()
            }

            selectAllBtn.isChecked = isSelectAll

            Log.e(TAG, "select all button: ${selectAllBtn.isSelected}" )
            Log.e(TAG, "select all button: ${selectAllBtn.isChecked}" )
        }

        delBtn.setOnClickListener {
            Log.e(TAG, "delete button click")
        }
    }

    fun whenSelectAll(isSelectAll: Boolean) {
        selectAllBtn.isChecked = isSelectAll
    }

    fun triggerNavBar(show: Boolean) {
        if (show) {
            navBar.visibility = View.VISIBLE
            deleteBar.visibility = View.GONE
        } else {
            navBar.visibility = View.GONE
            deleteBar.visibility = View.VISIBLE
        }
    }

    @SuppressLint("ResourceType")
    override fun finish() {
        val curTime = System.currentTimeMillis()
        if (curTime - lastBackTime > 2000) {
            lastBackTime = curTime
            SnackbarUtils.with(window.decorView)
                    .setMessage("再次返回退出")
                    .setBgResource(R.color.colorPrimary)
                    .show()
        } else {
            super.finish()
        }
    }

    companion object {
        const val TAG = "NestFragmentActivity"
    }

    interface Editable{
        fun selectAll()
        fun cleanAll()
        fun isSelectAll() : Boolean
    }
}