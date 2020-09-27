package com.lyhux.yuedunovel.data.db

import androidx.room.Dao
import androidx.room.Query
import com.lyhux.yuedunovel.data.BookBean

/**
 * @author Zsc
 * @date   2019/5/18
 * @desc
 */
@Dao
interface BookDao : BaseDao<BookBean> {

    @Query("SELECT * FROM book WHERE id=:bookId")
    fun findOne(bookId: String): BookBean?

    @Query("SELECT * FROM book")
    fun findAll(): List<BookBean>

    // @Query("SELECT id FROM book WHERE isLocal=:isLocal")
    // fun getAllLocalPath(isLocal: Boolean = true): List<String>

}