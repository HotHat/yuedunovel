package com.lyhux.yuedunovel.data.db

import androidx.room.*

@Dao
interface BookshelfDao {

    // @Query("UPDATE bookshelf set ")
    // fun update(now: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(record: BookshelfBean)

    @Query("Select * from bookshelf order by created_at desc")
    fun getAll() : List<BookshelfBean>

    @Delete
    fun delete(bookshelfBean: BookshelfBean)
}
