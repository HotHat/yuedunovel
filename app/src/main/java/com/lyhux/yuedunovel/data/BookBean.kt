package com.lyhux.yuedunovel.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookBean(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "author") var author: String

)