package com.lyhux.yuedunovel.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lyhux.yuedunovel.data.BookshelfBean

@Dao
interface BookshelfDao {

    // @Query("UPDATE bookshelf set ")
    // fun update(now: String)

    @Insert
    fun insert(record: BookshelfBean)

    @Query("Select * from bookshelf")
    fun findAll() : List<BookshelfBean>

    @Delete
    fun delete(bookshelfBean: BookshelfBean)
}
