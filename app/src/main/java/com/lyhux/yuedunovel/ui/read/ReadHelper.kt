package com.lyhux.yuedunovel.ui.read

import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.data.ChapterBean
import com.page.view.data.TxtChapter
import kotlinx.coroutines.*

/**
 * @author Zsc
 * @date   2019/5/4
 * @desc
 */
object ReadHelper {


    fun loadChapters(bookApi: BookApi, bookId: String, iBookChapters: IBookChapters) {

        iBookChapters.bookChapters(listOf(ChapterBean(
                "test_1234",
                "test_001",
                "book chapter test content1",
                "test_001"
        ),ChapterBean(
                "test_1234",
                "test_002",
                "book chapter test content2",
                "test_002"
        ), ChapterBean(
                "test_1234",
                "test_003",
                "book chapter test content3",
                "test_003"
        )))
        // val a = bookApi.bookChaptersAsync(bookId)
        // GlobalScope.launch(Dispatchers.IO) {
        //     val result = a.awaitResult()
        //     withContext(Dispatchers.Main) {
        //         result.doSuccess {
        //             iBookChapters.bookChapters(it)
        //         }.doError {
        //             ToastUtils.showShort("加载失败")
        //         }
        //     }
        // }
    }


    fun loadContent(
            bookApi: BookApi, bookId: String,
            bookChapterList: List<TxtChapter>
            , iBookChapters: ReadFragment
    ) {
        //取消上次的任务，防止多次加载
        //首先判断是否Chapter已经存在
        val listDeferred = mutableListOf<Deferred<ChapterBean>>()
        listDeferred.addAll(
            bookChapterList.map {
                bookApi.bookChapterAsync2(it.link ?: "")
            }
        )
        GlobalScope.launch(Dispatchers.IO) {
            val list = listDeferred.awaitAll()
            withContext(Dispatchers.Main) {
                if (list.any {
                        it.content.isNullOrEmpty()
                    }) {
                    iBookChapters.errorChapters()
                } else {
                    list.forEach {
                        BookCacheUtils.saveChapterInfo(bookId, it.title, it.content ?: "")
                    }
                    iBookChapters.finishChapters()
                }
            }
        }
    }


}