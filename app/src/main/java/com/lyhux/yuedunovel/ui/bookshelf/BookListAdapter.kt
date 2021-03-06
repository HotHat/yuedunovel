package com.lyhux.yuedunovel.ui.bookshelf

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.BaseRecord

class BookListAdapter(private var context: Context, private var recordList: List<BookshelfItemBean>) : BaseAdapter() {

    private var isSelectMode = false

    override fun getView(pos: Int, contentView: View?, parent: ViewGroup?): View {
        val item = recordList[pos]

        if (contentView == null)  {
            val view = LayoutInflater.from(context).inflate(R.layout.fragment_bookshelf_item, null)

            val imageView = view.findViewById<ImageView>(R.id.fg_bookshelf_item_img)
            Glide.with(context)
                    .load(item.bookCover)
                    .placeholder(R.drawable.ic_bookshelf_search)
                    .into(imageView);

            val title = view.findViewById<TextView>(R.id.fg_bookshelf_item_title)

            title.text = item.bookTitle

            val mask = view.findViewById<View>(R.id.fg_item_overlay)
            val sel = view.findViewById<RadioButton>(R.id.fg_item_sel)

            if (isSelectMode) {
                mask.visibility = View.VISIBLE
                sel.visibility = View.VISIBLE
            } else {
                mask.visibility = View.GONE
                sel.visibility = View.GONE
            }
            // 显示选中
            sel.isChecked = item.isSelected

            return view
        } else {
            val mask = contentView.findViewById<View>(R.id.fg_item_overlay)
            val sel = contentView.findViewById<RadioButton>(R.id.fg_item_sel)

            if (isSelectMode) {
                mask.visibility = View.VISIBLE
                sel.visibility = View.VISIBLE
            } else {
                mask.visibility = View.GONE
                sel.visibility = View.GONE
            }

            // 显示选中
            sel.isChecked = item.isSelected
        }

        return contentView
    }

    override fun getItem(pos: Int): Any {
        return recordList[pos]
    }

    override fun getItemId(p0: Int): Long {
        return 0;
    }

    override fun getCount(): Int {
        return recordList.size
    }

    fun selectItem(pos: Int) {
        if (isSelectMode) {
            val it = recordList[pos]
            it.isSelected = !it.isSelected
            this.notifyDataSetChanged()
        }
    }

    fun selectAll() {
        for (it in recordList) {
            it.isSelected = true
        }
        notifyDataSetChanged()
    }

    // 是否已经全选
    fun isSelectAll(): Boolean {
        for (it in recordList) {
            if (!it.isSelected)
                return false
        }
        return true
    }

    fun cleanAll() {
        for (it in recordList) {
            it.isSelected = false
        }
        notifyDataSetChanged()
    }

    fun setMode(isSelectMode: Boolean) {
        this.isSelectMode = isSelectMode
        this.notifyDataSetChanged()
    }

}