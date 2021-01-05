package com.lyhux.yuedunovel.data.repository

import com.lyhux.yuedunovel.data.db.BookshelfBean
import com.lyhux.yuedunovel.data.db.ReadingRecordBean
import com.lyhux.yuedunovel.data.db.ReadingRecordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

object ReadingRecordRepository: KoinComponent {

    private val readingRecordDao: ReadingRecordDao by inject()


    suspend fun insert(record: ReadingRecordBean) {
        return withContext(Dispatchers.IO) {
            readingRecordDao.addReadingRecord(record)
        }
    }

    suspend fun findByBookId(bookId: String): ReadingRecordBean? {
        return withContext(Dispatchers.IO) {
            readingRecordDao.findByBookId(bookId)
        }
    }

    suspend fun getAll(): List<ReadingRecordBean> {
        return withContext(Dispatchers.IO) {
            readingRecordDao.getAll()
        }
    }

    suspend fun delete(record: ReadingRecordBean) {
        return withContext(Dispatchers.IO) {
            readingRecordDao.delete(record)
        }
    }

}