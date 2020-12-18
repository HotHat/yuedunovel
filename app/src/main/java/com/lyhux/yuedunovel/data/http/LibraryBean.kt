package com.lyhux.yuedunovel.data.http

import com.google.gson.annotations.SerializedName


data class LibraryBean(
        var carouse: List<CarouseItem>,
        var category: List<String>,
        var block: List<String>

)

data class CarouseItem(
        var image: String,
        var type: Int,
        @SerializedName("in_app_type")
        var inAppType: Int,
        var data: String
)

data class BlockItem(
        var category: String,
        var items: List<BlockBookItem>
)

data class BlockBookItem(
        var cover: String,
        @SerializedName("book_id")
        var bookId: String
)