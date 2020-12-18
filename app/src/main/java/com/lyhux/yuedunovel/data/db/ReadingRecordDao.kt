package com.lyhux.yuedunovel.data.db

import androidx.room.Dao
import androidx.room.Query

/**
 * @author Zsc
 * @date   2019/5/18
 * @desc
 */
@Dao
interface ReadingRecordDao:BaseDao<ReadingRecordBean> {

    @Query("SELECT * FROM book_record")
    fun getAll(): List<ReadingRecordBean>

}