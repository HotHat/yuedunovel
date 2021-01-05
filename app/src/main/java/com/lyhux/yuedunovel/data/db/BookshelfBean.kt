package com.lyhux.yuedunovel.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.lyhux.yuedunovel.data.BaseRecord
import java.util.*

/**
 * 阅读书架(收藏) fixme 不属于控件必须要的
 */
@Entity(tableName = "bookshelf")
class BookshelfBean: BaseRecord {
    //所属的书的id
    @PrimaryKey
    @ColumnInfo(name="book_id")
    override var bookId: String = ""
    // 标题
    @ColumnInfo(name="title")
    override var bookTitle: String = ""

    // 封面
    @ColumnInfo(name="book_cover")
    override var bookCover: String = ""

    @ColumnInfo(name="created_at")
    @TypeConverters(Converters::class)
    var bookTime: Date? = null


}
