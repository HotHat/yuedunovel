package com.lyhux.yuedunovel.data.http

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BookDetailBean (
    @SerializedName("book_id")
    var bookId: String = "",
    // 标题
    @SerializedName("name")
    var bookName: String = "",

    // 封面
    @SerializedName("cover")
    var bookCover: String = "",

    @SerializedName("author")
    var bookAuthor: String = "",

    @SerializedName("status")
    var bookStatus: String = "",

    @SerializedName("description")
    var bookDesc: String = "",

    @SerializedName("popular_count")
    var popularCount: String = "",

    @SerializedName("read_count")
    var readCount: String = "",

    @SerializedName("words_count")
    var wordsCount: String = "",

    @SerializedName("last_chapter")
    var lastChapter: String = "",

    @SerializedName("updated_date")
    var updatedDate: String = ""

)