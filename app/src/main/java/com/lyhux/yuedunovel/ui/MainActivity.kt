package com.lyhux.yuedunovel.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.BookshelfBean
import com.lyhux.yuedunovel.data.db.BookshelfDao
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import java.util.*

class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var bookId: TextView
    private lateinit var bookTitle: TextView
    private lateinit var bookCover: TextView
    private lateinit var btn: Button

    private  val bookshelfDao: BookshelfDao by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        bookId = findViewById(R.id.book_id)
        bookTitle = findViewById(R.id.book_title)
        bookCover = findViewById(R.id.book_cover)
        btn = findViewById(R.id.button)


        btn.setOnClickListener(View.OnClickListener {
            Log.e(TAG, "On clicked")
            val id = bookId.text.toString()
            val title = bookTitle.text.toString()
            val cover = bookCover.text.toString()
            Log.e(TAG, id)
            Log.e(TAG, title)
            Log.e(TAG, cover)
            val date: Date = Date(System.currentTimeMillis())

            var bean = BookshelfBean()
            bean.bookId = id

            // TODO: change to viewmodel
            bookshelfDao.insert(BookshelfBean().apply {
                bookId = id
                bookCover = cover
                bookTitle = title
                bookTime = date
            })


            Log.e(TAG, "All in database")

            // TODO: change to viewmodel
            val allRecord = bookshelfDao.findAll()
            for (r in allRecord) {
                Log.e(TAG, r.bookId)
                Log.e(TAG, r.bookTitle)
                Log.e(TAG, r.bookCover)
                Log.e(TAG, r.bookTime?.time.toString())
            }

        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}