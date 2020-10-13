package com.lyhux.yuedunovel.data

interface BaseRecord {
    // 所属的书的id
    var bookId: String

    // 标题
    var bookTitle: String

    // 封面
    var bookCover: String
}