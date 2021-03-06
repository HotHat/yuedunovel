package com.lyhux.yuedunovel.cv

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.ui.book.BookActivity


class StoreCategoryBlockView : LinearLayout {

    // constructor(context: Context?) : super(context) {
    //
    // }

    private lateinit var titleText: TextView
    private lateinit var moreText: TextView
    private var imgArr: ArrayList<ImageView>
    private var titleArr: ArrayList<TextView>

    // private lateinit var adapter: ArrayList<ImageTextItem>


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        LayoutInflater.from(context!!).inflate(R.layout.store_category_block, this)

        imgArr = ArrayList(MAX_ITEM)
        titleArr = ArrayList(MAX_ITEM)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        titleText = findViewById(R.id.title)
        moreText = findViewById(R.id.more)
        moreText.setOnClickListener {
            Log.e(TAG, "more click")
        }

        // image array
        imgArr.add(findViewById(R.id.img1))
        imgArr.add(findViewById(R.id.img2))
        imgArr.add(findViewById(R.id.img3))
        imgArr.add(findViewById(R.id.img4))
        imgArr.add(findViewById(R.id.img5))
        imgArr.add(findViewById(R.id.img6))

        // text array
        titleArr.add(findViewById(R.id.title1))
        titleArr.add(findViewById(R.id.title2))
        titleArr.add(findViewById(R.id.title3))
        titleArr.add(findViewById(R.id.title4))
        titleArr.add(findViewById(R.id.title5))
        titleArr.add(findViewById(R.id.title6))

    }

    fun setMoreCategory(category: String) {
        titleText.text = category
        moreText.setOnClickListener {
            Log.e(TAG, "more go to category $category")
        }

    }

    fun setContent(items: List<ImageTextItem>) {
        // adapter = items

        for ((idx, item) in items.withIndex()) {
            if (idx < MAX_ITEM) {
                val img = imgArr[idx]
                val title = titleArr[idx]
                // setImimg.setIm(item.imageUrl)
                Glide.with(context)
                        .load(item.imageUrl)
                        .placeholder(R.drawable.ic_bookshelf_search)
                        .into(img);
                title.text = item.title
                img.setOnClickListener {
                    val intent = Intent(context, BookActivity::class.java).apply {
                        putExtra("book_id", item.bookId)
                    }
                    context.startActivity(intent)
                }
            }
        }

    }

    companion object {
        const val MAX_ITEM = 6
        const val TAG = "StoreCategoryBlockView"
    }

}