package com.lyhux.yuedunovel.ui.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.db.BookshelfBean
import org.koin.android.ext.android.inject
import java.util.*

private const val BOOK_ID = "book_id"

/**
 * A simple [Fragment] subclass.
 * Use the [BookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookFragment : Fragment() {
    private var bookId: String? = null
    private val bookViewModel: BookViewModel by inject()
    private var rootView: View? = null

    private var bookshelfBean: BookshelfBean? = null

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

    private lateinit var addBookshelfView: Button
    private lateinit var readView: Button

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

        val ctx = requireContext()
        Log.e(TAG, "run once in onCreate")

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
                        .placeholder(R.drawable.ic_bookshelf_search)
                        .into(bookCoverView);


                bookViewModel.findByBookId(bookDetail.bookId)

                bookshelfBean = BookshelfBean().apply {
                    bookId = bookDetail.bookId
                    bookTitle = bookDetail.bookName
                    bookCover = bookDetail.bookCover
                    bookTime = Date()
                }

            }
            .doError {

            }
        })

        bookViewModel.bookshelfLiveData.observe(this, Observer {
            if (it == null && bookshelfBean != null) {
                bookViewModel.addBookshelf(bookshelfBean!!)
            }
            addBookshelfView.text = "已加入书架"
            addBookshelfView.isClickable = false
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (rootView == null) {
            Log.e(TAG, "run once in onCreateView")
            rootView = inflater.inflate(R.layout.fragment_book, container, false)
            bookViewModel.getDetail()
        } else {
            Log.e(TAG, "run old view in onCreateView")
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chapter = view.findViewById<LinearLayout>(R.id.fg_book_detail_chapter)
        chapter.setOnClickListener {
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

        addBookshelfView = view.findViewById(R.id.frag_book_add_bookshelf)
        readView = view.findViewById(R.id.frag_book_read)



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