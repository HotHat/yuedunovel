package com.lyhux.yuedunovel.ui.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.lyhux.yuedunovel.R
import org.koin.android.ext.android.inject

private const val BOOK_ID = "book_id"

/**
 * A simple [Fragment] subclass.
 * Use the [BookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookFragment : Fragment() {
    private var bookId: String? = null
    private val bookViewModel: BookViewModel by inject()

    private lateinit var bookCoverView: ImageView
    private lateinit var bookAuthorView: TextView
    private lateinit var bookNameView: TextView
    private lateinit var bookStatusView: TextView
    private lateinit var readCountView: TextView
    private lateinit var popularCountView: TextView
    private lateinit var wordsCountView: TextView
    private lateinit var bookDescView: TextView
    private lateinit var bookLastChapterView: TextView
    private lateinit var bookUpdateDateView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getString(BOOK_ID)
        }

        Log.e(TAG, arguments.toString())
        Log.e(TAG, arguments?.getString("book_id") ?: "Cant't get book id")

        if (bookId == "") {
            requireActivity().finish()
        }

        bookViewModel.getDetail()
        val ctx = requireContext()

        bookViewModel.response.observe(this, Observer {
            it.doSuccess {bookDetail ->
                bookAuthorView.text = bookDetail.bookAuthor
                bookNameView.text = bookDetail.bookName
                bookDescView.text = bookDetail.bookDesc
                bookStatusView.text = bookDetail.bookStatus
                readCountView.text = bookDetail.readCount
                popularCountView.text = bookDetail.popularCount
                wordsCountView.text = bookDetail.wordsCount
                bookLastChapterView.text = bookDetail.lastChapter
                bookUpdateDateView.text = bookDetail.updatedDate

                // 封面
                Glide.with(ctx)
                        .load(bookDetail.bookCover)
                        .placeholder(R.drawable.book_shelf_search)
                        .into(bookCoverView);
            }
            .doError {

            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book, container, false)

        val chapter = view.findViewById<LinearLayout>(R.id.fg_book_detail_chapter)
        chapter.setOnClickListener {
            val action =
            view.findNavController().navigate(R.id.nav_graph_book_chapter_fragment, Bundle().apply {
                putString(BOOK_ID, bookId)
            })
        }

        bookCoverView = view.findViewById(R.id.frag_book_cover)
        bookNameView = view.findViewById(R.id.frag_book_name)
        bookAuthorView = view.findViewById(R.id.frag_book_author)
        bookStatusView = view.findViewById(R.id.frag_book_category_status)
        bookDescView = view.findViewById(R.id.frag_book_desc)
        readCountView = view.findViewById(R.id.frag_book_read_count)
        popularCountView = view.findViewById(R.id.frag_book_popular_count)
        wordsCountView = view.findViewById(R.id.frag_book_words_count)
        bookLastChapterView = view.findViewById(R.id.frag_book_last_chapter)
        bookUpdateDateView = view.findViewById(R.id.frag_book_updated_date)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment BookFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(bookId: String) =
                BookFragment().apply {
                    arguments = Bundle().apply {
                        putString(BOOK_ID, bookId)
                    }
                }
        const val TAG = "BookFragment"
    }
}