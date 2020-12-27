package com.lyhux.yuedunovel.data.http

import com.google.gson.annotations.SerializedName


/**
 * 章节标题信息
 */
data class ChapterItemBean(
        @SerializedName("book_id")
        var bookId: String,
        @SerializedName("chapter_id")
        var chapterId: String,
        var title: String
) {
        override fun toString(): String {
            return title
        }
}