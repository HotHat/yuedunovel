package com.lyhux.yuedunovel.data.db

import androidx.room.*

/**
 * @author Zsc
 * @date   2019/5/18
 * @desc
 */
@Dao
interface ReadingRecordDao:BaseDao<ReadingRecordBean> {

    @Query("SELECT * FROM book_reading_record")
    fun getAll(): List<ReadingRecordBean>


    @Query("SELECT * FROM book_reading_record WHERE book_id= :bookId")
    fun findByBookId(bookId: String): ReadingRecordBean?

    @Update
    fun updateRecord(record: ReadingRecordBean)

    @Delete
    fun deleteRecord(record: ReadingRecordBean)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addReadingRecord(record: ReadingRecordBean)
}