package com.lyhux.yuedunovel.ui.bookshelf

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lyhux.yuedunovel.R

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
        TEXT_BIG_SIZE =  resources.getDimension(R.dimen.bookshelf_big)
        TEXT_SMALL_SIZE = resources.getDimension(R.dimen.bookshelf_small)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            COLOR_RED = resources.getColor(R.color.colorRed, null)
            COLOR_BLACK = resources.getColor(R.color.colorBlack, null)
        } else {
            COLOR_RED = resources.getColor(R.color.colorRed)
            COLOR_BLACK = resources.getColor(R.color.colorBlack)
        }

        val view = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        val viewPage = view.findViewById<ViewPager2>(R.id.view_page)

        val toolbar  = view.findViewById<Toolbar>(R.id.tool_bar)

        val adapter = ViewPagerAdapter(childFragmentManager, this.lifecycle, toolbar)
        adapter.addFrag(BookFragment.newInstance(), "first Inner fragment")
        adapter.addFrag(RecordFragment.newInstance(), "second Inner fragment")

        viewPage.adapter = adapter

        return view
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle, toolbar: Toolbar) :
            FragmentStateAdapter(manager, lifecycle) {

        private val shelf: TextView = toolbar.findViewById<TextView>(R.id.toolbar_bookshelf)
        private val log: TextView = toolbar.findViewById<TextView>(R.id.toolbar_reading_log)

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

            //            if (position == 0) {
            //                shelf.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0F)
            //                shelf.setTextColor(Color.parseColor("#FF0000"))
            //                log.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0F)
            //                log.setTextColor(Color.parseColor("#000000"))
            //            } else {
            //                log.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0F)
            //                log.setTextColor(Color.parseColor("#FF0000"))
            //                shelf.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0F)
            //                shelf.setTextColor(Color.parseColor("#000000"))
            //            }
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
        @JvmStatic
        fun newInstance() =
                BookshelfFragment().apply {
                    arguments = Bundle().apply {
                        // putString(ARG_PARAM1, param1)
                        // putString(ARG_PARAM2, param2)
                    }
                }
    }
}