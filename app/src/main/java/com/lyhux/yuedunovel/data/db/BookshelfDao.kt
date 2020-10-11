package com.lyhux.yuedunovel.data.db

import androidx.room.*
import com.lyhux.yuedunovel.data.BookshelfBean

@Dao
interface BookshelfDao {

    // @Query("UPDATE bookshelf set ")
    // fun update(now: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(record: BookshelfBean)

    @Query("Select * from bookshelf order by created_at desc")
    fun findAll() : List<BookshelfBean>

    @Delete
    fun delete(bookshelfBean: BookshelfBean)
}
