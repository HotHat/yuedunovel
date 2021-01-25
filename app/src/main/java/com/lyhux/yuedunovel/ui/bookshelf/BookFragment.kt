package com.lyhux.yuedunovel.ui.bookshelf

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.data.db.BookshelfBean
import com.lyhux.yuedunovel.data.repository.BookshelfRepository
import com.lyhux.yuedunovel.ui.NestFragmentActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAlarm.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookFragment: Fragment(), BookshelfFragment.EditableFragment, NestFragmentActivity.Editable {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val liveData: MutableLiveData<List<BookshelfBean>> = MutableLiveData()

    private  lateinit var gridView: GridView
    private var mAdapter: BookListAdapter? = null

    private lateinit var localReceiver: LocalReceiver
    private lateinit var localBroadcastManager: LocalBroadcastManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        localBroadcastManager = LocalBroadcastManager.getInstance(context); // 获取实例

        val intentFilter = IntentFilter()
        intentFilter.addAction("com.lyhux.yuedu.MyReceiver")
        localReceiver = LocalReceiver()
        localBroadcastManager.registerReceiver(localReceiver, intentFilter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_bookshelf_book, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(localReceiver)
    }

    inner class LocalReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@BookFragment.addAdapter()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG, "on view created")

        gridView = view.findViewById(R.id.fg_book_grid_view)

        gridView.setOnItemClickListener { adapterView, view, pos, l ->

            val adapter = gridView.adapter as BookListAdapter
            adapter.selectItem(pos)
            Log.e(TAG, "$pos")

            //
            val activity = requireActivity() as NestFragmentActivity
            activity.whenSelectAll(this.isSelectAll())
        }

        liveData.observe(viewLifecycleOwner, Observer {
            val items = it.map { bean ->
                BookshelfItemBean(bean.bookId, bean.bookCover, bean.bookTitle, false)
            }
            mAdapter = BookListAdapter(requireContext(), items)
            gridView.adapter = mAdapter
        })

        addAdapter()
    }

    override fun setEditMode(isEditMode: Boolean) {
        mAdapter?.setMode(isEditMode)
    }

    override fun selectAll() {
        mAdapter?.selectAll()
    }

    override fun cleanAll() {
        mAdapter?.cleanAll()
    }

    override fun isSelectAll() : Boolean {
        return mAdapter?.isSelectAll() ?: false
    }


    private fun addAdapter() {
        GlobalScope.launch {
            val bookshelfList = BookshelfRepository.getAll()

            liveData.postValue(bookshelfList)

        }
    }

    companion object {

        const val TAG = "BookFragment"
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