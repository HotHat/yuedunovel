package com.lyhux.yuedunovel.data.http

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class BookDetailBean {
    @SerializedName("book_id")
    var bookId: String = ""
    // 标题
    @SerializedName("name")
    var bookName: String = ""

    // 封面
    @SerializedName("cover")
    var bookCover: String = ""

    @SerializedName("author")
    var bookAuthor: String = ""

    @SerializedName("update_time")
    var updateTime : String = ""

    @SerializedName("total_clicks")
    var totalClicks: Int = 0

    @SerializedName("total_words")
    var totalWords: Int = 0

    var status: String = ""
}