package com.lyhux.yuedunovel.data.db

import androidx.room.*

@Dao
interface BookshelfDao {

    // @Query("UPDATE bookshelf set ")
    // fun update(now: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBookshelf(bookshelf: BookshelfBean)

    @Query("Select * from bookshelf where book_id=:bookId")
    fun findByBookId(bookId: String): BookshelfBean?

    @Query("Select * from bookshelf order by created_at desc")
    fun getAll() : List<BookshelfBean>

    @Delete
    fun delete(bookshelfBean: BookshelfBean)
}
