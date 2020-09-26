package com.lyhux.yuedunovel.cv

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.lyhux.yuedunovel.R


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

    fun setMoreCategory(category: Int) {
        moreText.setOnClickListener {
            Log.e(TAG, "more go to category $category")
        }

    }

    fun setContent(items: ArrayList<ImageTextItem>) {
        // adapter = items

        for ((idx, item) in items.withIndex()) {
            if (idx < MAX_ITEM) {
                val img = imgArr[idx]
                val title = titleArr[idx]
                // setImimg.setIm(item.imageUrl)
                title.text = item.title
                img.setOnClickListener {
                    Log.e(TAG, "novel book id: ${item.bookId}")
                }
            }
        }

    }

    companion object {
        const val MAX_ITEM = 6
        const val TAG = "StoreCategoryBlockView"
    }

}