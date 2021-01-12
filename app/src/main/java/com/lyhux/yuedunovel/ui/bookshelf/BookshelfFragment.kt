package com.lyhux.yuedunovel.ui.bookshelf

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.ui.NestFragmentActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookshelfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookshelfFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var TEXT_BIG_SIZE: Float = 0F
    private var TEXT_SMALL_SIZE: Float = 0F
    private var COLOR_RED: Int = 0
    private var COLOR_BLACK: Int = 0
    private var isEditMode = false

    private lateinit var mViewPage: ViewPager2

    // private val

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
        Log.e(TAG, "create view")
        return inflater.inflate(R.layout.fragment_bookshelf, container, false)
    }

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e(TAG, "view created")

        // Inflate the layout for this fragment
        TEXT_BIG_SIZE =  resources.getDimension(R.dimen.bookshelf_big)
        TEXT_SMALL_SIZE = resources.getDimension(R.dimen.bookshelf_small)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            COLOR_RED = resources.getColor(R.color.colorRed, null)
            COLOR_BLACK = resources.getColor(R.color.colorBlack, null)
        } else {
            COLOR_RED = resources.getColor(R.color.colorRed)
            COLOR_BLACK = resources.getColor(R.color.colorBlack)
        }


        mViewPage = view.findViewById<ViewPager2>(R.id.view_page)

        val toolbar  = view.findViewById<Toolbar>(R.id.tool_bar)
        val activity = requireActivity() as NestFragmentActivity
        if (activity.actionBar != null) {
            activity.actionBar?.hide()
        } else {
            activity.setSupportActionBar(toolbar)
        }

        val adapter = ViewPagerAdapter(childFragmentManager, this.lifecycle, toolbar)
        adapter.addFrag(BookFragment.newInstance(), "first Inner fragment")
        adapter.addFrag(RecordFragment.newInstance(), "second Inner fragment")

        val edit = toolbar.findViewById<ImageView>(R.id.frag_bookshelf_edit)
        edit.setOnClickListener {
            activity.triggerNavBar(isEditMode)
            isEditMode = !isEditMode

            editOnClick(isEditMode)
        }

        toolbar.setOnTouchListener { view, motionEvent ->
            view.findViewById<LinearLayout>(R.id.frag_bookshelf_toolbar).dispatchTouchEvent(motionEvent)}

        mViewPage.adapter = adapter


        super.onViewCreated(view, savedInstanceState)
    }

    private fun editOnClick(isEditMode: Boolean) {
        val adapter = mViewPage.adapter as ViewPagerAdapter
        val cur = adapter.createFragment(mViewPage.currentItem) as EditableFragment

        cur.setEditMode(isEditMode)
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle, toolbar: Toolbar) :
            FragmentStateAdapter(manager, lifecycle) {

        private val shelf: TextView = toolbar.findViewById(R.id.toolbar_bookshelf)
        private val log: TextView = toolbar.findViewById(R.id.toolbar_reading_log)

        //        val bigSize = text;

        private val mFragmentList: MutableList<Fragment> =
                ArrayList()

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return mFragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return mFragmentList[position]
        }

        @SuppressLint("ResourceAsColor")
        override fun getItemId(position: Int): Long {

            if (position == 0) {
                shelf.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_BIG_SIZE)
                shelf.setTextColor(COLOR_RED)
                log.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SMALL_SIZE)
                log.setTextColor(COLOR_BLACK)
            } else {
                log.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_BIG_SIZE)
                log.setTextColor(COLOR_RED)
                shelf.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SMALL_SIZE)
                shelf.setTextColor(COLOR_BLACK)
            }

            return super.getItemId(position)
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
        const val TAG = "bookshelf_fragment"
        @JvmStatic
        fun newInstance() =
                BookshelfFragment().apply {
                    arguments = Bundle().apply {
                        // putString(ARG_PARAM1, param1)
                        // putString(ARG_PARAM2, param2)
                    }
                }
    }


    interface EditableFragment {
        fun setEditMode(isEditMode: Boolean)
        fun selectAll()
        fun cleanAll()
    }
}