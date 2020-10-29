package com.lyhux.yuedunovel.ui.read

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Environment
import android.os.PowerManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.BookRecordBean
import com.page.view.DataConfig
import com.page.view.PageLoader
import com.page.view.PageView
import com.page.view.data.BaseRecord
import com.page.view.data.CollBookBean


private const val BOOK_ID = "book_id"
private const val CHAPTER_ID= "chapter_id"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var bookId: String? = null
    private var chapterId: String? = null

    private lateinit var mPageLoader: PageLoader
    private lateinit var mCollBook: CollBookBean

    private lateinit var mPvReadPage: PageView

    // r接收电池信息和时间更新的广播
    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(content: Context, intent: Intent) {
            // 电池变化
            if (intent.action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra("level", 0)
                mPageLoader.updateBattery(level)
            }
            // 时间变化
            else if (intent.action == Intent.ACTION_TIME_CHANGED) {
                mPageLoader.updateTime()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getString(BOOK_ID)
            chapterId = it.getString(CHAPTER_ID)
        }



    }

    @SuppressLint("InvalidWakeLockTag")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_read, container, false)

        mPvReadPage = view.findViewById(R.id.pv_read_page)

        mPageLoader = mPvReadPage.getPageLoader(true)


        mCollBook = CollBookBean().apply {
            isLocal = true
            bookId = requireContext().getExternalFilesDir("/frxx.txt")!!.path
        }

        mPageLoader.dataConfig = object : DataConfig {
            override fun saveRecord(bookRecord: BaseRecord) {

            }

            override fun getRecordById(bookId: String): BaseRecord {
                return BookRecordBean()
            }

        }

        mPageLoader.openBook(mCollBook)

        //注册广播
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        requireActivity().registerReceiver(mReceiver, intentFilter)

        //初始化屏幕常亮类
        // val pm = requireActivity().getSystemService(Context.POWER_SERVICE) as PowerManager
        // pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "keep bright")


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReadFragment.
         */
        private const val TAG = "ReadFragment"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ReadFragment().apply {
                    arguments = Bundle().apply {
                        putString(BOOK_ID, param1)
                        putString(CHAPTER_ID, param2)
                    }
                }
    }
}