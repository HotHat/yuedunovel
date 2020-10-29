package com.lyhux.yuedunovel.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.page.view.data.BaseRecord

/**
 * Created by LiangLu on 17-11-22.
 * 阅读记录 fixme 不属于控件必须要的
 */
@Entity(tableName = "book_record")
class BookRecordBean: BaseRecord {
    //所属的书的id
    @PrimaryKey
    @ColumnInfo(name="book_id")
    override var bookId: String = ""
    // 标题
    @ColumnInfo(name="title")
    var bookTitle: String = ""
    // 封面
    @ColumnInfo(name="book_cover")
    var bookCover: String = ""

    //当前的页码
    @ColumnInfo(name="page_pos")
    override var pagePos: Int = 0

    //阅读到了第几章
    @ColumnInfo(name="chapter")
    override var chapter: Int = 0


}
