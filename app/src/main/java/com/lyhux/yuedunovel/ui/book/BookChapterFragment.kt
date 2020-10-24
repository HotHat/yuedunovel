package com.lyhux.yuedunovel.ui.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyhux.yuedunovel.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "book_id"

/**
 * A simple [Fragment] subclass.
 * Use the [BookChapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookChapterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var bookId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getString(ARG_PARAM1)
            bookId?.let { it1 -> Log.e(TAG, it1) };
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_chapter, container, false)
    }

    companion object {
        const val TAG = "BookChapterFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param bookId Parameter 1.
         * @return A new instance of fragment BookChapterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(bookId: String) =
                BookChapterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, bookId)
                    }
                }
    }
}