package com.lyhux.yuedunovel.ui.book

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.data.http.ChapterItemBean
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

private const val ARG_PARAM1 = "book_id"

/**
 * A simple [Fragment] subclass.
 * Use the [BookChapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookChapterFragment : Fragment(), KoinComponent {

    private var bookId: String? = null
    private val bookApi: BookApi by inject()
    private lateinit var adapter: ArrayAdapter<ChapterItemBean>

    private lateinit var listItems: List<ChapterItemBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookId = it.getString(ARG_PARAM1)
        }

        Log.e(TAG, bookId ?: "Can't get bookId in chapter fragment")



        listItems = arrayListOf()


        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listItems)

        GlobalScope.launch {
            val response = bookApi.bookChapterListAsync(bookId!!).await()

            if (response.isSuccess) {
                val items = response.data!!
                Log.e(TAG, "start notify")
                notify(items)
                Log.e(TAG, "end notify")
                // adapter.notifyDataSetChanged()
                Log.e(TAG, items.toString())
            } else {
                Log.e(TAG, "api error: ${response.message}")
            }

        }

    }

    private suspend fun notify(items: List<ChapterItemBean>) {
        withContext(Main) {
            adapter.clear()
            adapter.addAll(items)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_chapter, container, false)

        val listView = view.findViewById<ListView>(R.id.fg_book_chapter_list_view)

        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            val chapterItem = adapterView.getItemAtPosition(i) as ChapterItemBean
            view.findNavController().navigate(R.id.nav_read_fragment, Bundle().apply {
                putString("book_id", bookId)
                putString("chapter_id", chapterItem.chapterId)
            })

        }

        return view
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