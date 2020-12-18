package com.lyhux.yuedunovel.ui.read

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.data.db.ReadingRecordBean
import com.lyhux.yuedunovel.data.http.ChapterItemBean
import com.page.view.DataConfig
import com.page.view.PageLoader
import com.page.view.PageView
import com.page.view.data.BaseRecord
import com.page.view.data.BookChapterBean
import com.page.view.data.CollBookBean
import com.page.view.data.TxtChapter
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent


private const val BOOK_ID = "book_id"
private const val CHAPTER_ID= "chapter_id"

/**
 * A simple [Fragment] subclass.
 * Use the [ReadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReadFragment : Fragment(), KoinComponent, IBookChapters {
    // TODO: Rename and change types of parameters
    private var bookId: String? = null
    private var chapterId: String? = null

    private val bookApi: BookApi by inject()

    private lateinit var mPageLoader: PageLoader
    private lateinit var mCollBook: CollBookBean

    private lateinit var mPvReadPage: PageView
    internal var mTxtChapters: MutableList<TxtChapter> = mutableListOf()
    private var bookChapterList: MutableList<BookChapterBean> = mutableListOf()

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

        mPageLoader = mPvReadPage.getPageLoader(false)


        // mCollBook = CollBookBean().apply {
            // isLocal = true
            // bookId = requireContext().getExternalFilesDir("/frxx2.txt")!!.path
        // }

        mCollBook = CollBookBean().apply {
            isLocal = false
            bookId = "test_1234"
        //     bookChapters = arrayListOf(BookChapterBean().apply {
        //         bookId = "test_1234"
        //         title = "test_000"
        //         link = "link1234"
        //     }, BookChapterBean().apply {
        //         bookId = "test_1234"
        //         title = "test_001"
        //         link = "link1234"
        //     })
        }



        mPageLoader.dataConfig = object : DataConfig {
            override fun saveRecord(bookRecord: BaseRecord) {

            }

            override fun getRecordById(bookId: String): BaseRecord {
                return ReadingRecordBean()
            }

        }


        //注册广播
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        requireActivity().registerReceiver(mReceiver, intentFilter)

        //初始化屏幕常亮类
        // val pm = requireActivity().getSystemService(Context.POWER_SERVICE) as PowerManager
        // pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "keep bright")

        mPvReadPage.setTouchListener(object : PageView.TouchListener {
            override fun center() {
                // toggleMenu()
            }

            override fun onTouch(): Boolean {
                // return !hideReadMenu()
                return true
            }

            override fun prePage(): Boolean {
                return true
            }

            override fun nextPage(): Boolean {
                return true
            }

            override fun cancel() {}
        })

        mPageLoader.setOnPageChangeListener(object : PageLoader.OnPageChangeListener {
            override fun onChapterChange(pos: Int) {
                setCategorySelect(pos)

            }

            override fun onLoadChapter(chapters: List<TxtChapter>, pos: Int) {
                ReadHelper.loadContent(bookApi, mCollBook.bookId, chapters, this@ReadFragment)
                setCategorySelect(mPageLoader.chapterPos)

                // if (mPageLoader.pageStatus == PageLoader.STATUS_LOADING
                //         || mPageLoader.pageStatus == PageLoader.STATUS_ERROR
                // ) {
                //     //冻结使用
                //     read_sb_chapter_progress.isEnabled = false
                // }
                //
                // //隐藏提示
                // read_tv_page_tip.isVisible = false
                // read_sb_chapter_progress.progress = 0
            }

            override fun onCategoryFinish(chapters: List<TxtChapter>) {
                mTxtChapters.clear()
                mTxtChapters.addAll(chapters)
                // mReadCategoryAdapter.notifyDataSetChanged()
            }

            override fun onPageCountChange(count: Int) {
            }

            override fun onPageChange(pos: Int) {

            }
        })


        ReadHelper.loadChapters(bookApi, "test_1234", this)

        // mPageLoader.openBook(mCollBook)
        //
        // mPageLoader.skipToChapter(0)

        return view
    }

    /**
     * 设置选中目录
     *
     * @param selectPos
     */
    private fun setCategorySelect(selectPos: Int) {
        for (i in mTxtChapters.indices) {
            val chapter = mTxtChapters[i]
            chapter.isSelect = i == selectPos
        }
        // mReadCategoryAdapter.notifyDataSetChanged()
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

    override fun errorChapters() {
        if (mPageLoader.pageStatus == PageLoader.STATUS_LOADING) {
            mPageLoader.chapterError()
        }
    }

    override fun finishChapters() {
        if (mPageLoader.pageStatus == PageLoader.STATUS_LOADING) {
            // pv_read_page.post {
            mPageLoader.openChapter()
                //当完成章节的时候，刷新列表
            //     mReadCategoryAdapter.notifyDataSetChanged()
            // }
        }
    }

    override fun bookChapters(bookChaptersBean: List<ChapterItemBean>) {
        bookChapterList.clear()
        // for (bean in bookChaptersBean.chapters) {
        //     val chapterBean = BookChapterBean()
        //     chapterBean.bookId = bookChaptersBean.book
        //     chapterBean.link = bean.link
        //     chapterBean.title = bean.title
        //     //chapterBean.setTaskName("下载");
        //     chapterBean.unreadble = bean.isRead
        //     bookChapterList.add(chapterBean)
        // }
        for (bean in bookChaptersBean) {
                bookChapterList.add(BookChapterBean().apply {
                    bookId = bean.bookId
                    title  = bean.title
                    // link   = bean.link
                })
        }
        mCollBook.bookChapters = bookChapterList
        //如果是更新加载，那么重置PageLoader的Chapter
        /*if (mCollBook.isUpdate() && isCollected) {
            mPageLoader.setChapterList(bookChapterList)
            //异步下载更新的内容存到数据库
            //fixme db
//            BookChapterHelper.getsInstance()
//                .saveBookChaptersWithAsync(bookChapterList)

        } else {*/
        mPageLoader.openBook(mCollBook)    }
}