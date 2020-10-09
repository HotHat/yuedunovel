package com.lyhux.yuedunovel.data.repository

import com.lyhux.yuedunovel.data.BookshelfBean
import com.lyhux.yuedunovel.data.db.BookshelfDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * @author Zsc
 * @date   2019/5/18
 * @desc
 */
object BookshelfRepository : KoinComponent {

    private val bookshelfDao: BookshelfDao by inject()


    suspend fun insert(bookshelfBean: BookshelfBean) {
        return withContext(Dispatchers.IO) {
            bookshelfDao.insert(bookshelfBean)
        }
    }

    suspend fun delete(bookBean: BookshelfBean) {
        return withContext(Dispatchers.IO) {
            bookshelfDao.delete(bookBean)
        }
    }

    suspend fun getAll(): List<BookshelfBean> {
        return withContext(Dispatchers.IO) {
            bookshelfDao.findAll()
        }
    }


}