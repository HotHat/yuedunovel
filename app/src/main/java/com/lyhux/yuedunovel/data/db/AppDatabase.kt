package com.lyhux.yuedunovel.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.BookRecordBean

@Database(
    entities = [
        // BookType::class,
        BookRecordBean::class,
        BookBean::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    // abstract fun bookTypeDao(): BookTypeDao
    abstract fun bookRecordDao(): BookRecordDao
    abstract fun bookDao(): BookDao

    companion object {

        private const val dbName = "weyue-db"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
                    .build()
        }
    }
}
