package com.lyhux.yuedunovel.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lyhux.yuedunovel.data.db.Converters
import java.util.*

/**
 * 阅读书架(收藏) fixme 不属于控件必须要的
 */
@Entity(tableName = "bookshelf")
class BookshelfBean {
    //所属的书的id
    @PrimaryKey
    @ColumnInfo(name="book_id")
    var bookId: String = ""
    // 标题
    @ColumnInfo(name="title")
    var bookTitle: String = ""

    // 封面
    @ColumnInfo(name="book_cover")
    var bookCover: String = ""

    @ColumnInfo(name="created_at")
    @TypeConverters(Converters::class)
    var bookTime: Date? = null


}
