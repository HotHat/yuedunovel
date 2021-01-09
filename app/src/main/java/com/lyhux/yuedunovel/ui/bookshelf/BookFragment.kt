package com.lyhux.yuedunovel.ui.bookshelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.BaseRecord
import com.lyhux.yuedunovel.data.db.BookshelfBean
import com.lyhux.yuedunovel.data.db.BookshelfDao
import com.lyhux.yuedunovel.data.http.ApiResponse
import com.lyhux.yuedunovel.data.http.BookDetailBean
import com.lyhux.yuedunovel.data.repository.BookshelfRepository
import com.lyhux.yuedunovel.koin.Injector
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.android.ext.android.inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAlarm.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookFragment: Fragment(), BookshelfFragment.EditableFragment {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val liveData: MutableLiveData<List<BookshelfBean>> = MutableLiveData()

    private  lateinit var gridView: GridView
    private var mAdapter: BookListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bookshelf_book, container, false)


        /*
        // val recordList = arrayListOf<BaseRecord>(BookshelfBean().apply {
        //     bookId = "a1234"
        //     bookTitle = "测试1"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a12345"
        //     bookTitle = "测试2"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a123456"
        //     bookTitle = "测试3"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a1234567"
        //     bookTitle = "测试4"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a1234567"
        //     bookTitle = "测试5"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a1234567"
        //     bookTitle = "测试6"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a1234567"
        //     bookTitle = "测试7"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }, BookshelfBean().apply {
        //     bookId = "a1234567"
        //     bookTitle = "测试8"
        //     bookCover = "https://lookimg.com/images/2020/09/17/P0OfQo.jpg"
        // }
        // )
       */

        // return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridView = view.findViewById(R.id.fg_book_grid_view)

        gridView.setOnItemClickListener { adapterView, view, pos, l ->

        }

        liveData.observe(viewLifecycleOwner, Observer {
            mAdapter = BookListAdapter(requireContext(), it)
            gridView.adapter = mAdapter
        })

        addAdapter()
    }

    override fun setEditMode(isEditMode: Boolean) {
        mAdapter?.setMode(isEditMode)
        mAdapter?.notifyDataSetChanged()
    }

    private fun addAdapter() {
        GlobalScope.launch {
            val bookshelfList = BookshelfRepository.getAll()

            liveData.postValue(bookshelfList)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAlarm.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            BookFragment().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                    // putString(ARG_PARAM2, param2)
                }
            }
    }
}