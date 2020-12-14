package com.lyhux.yuedunovel.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.cv.ImageTextItem
import com.lyhux.yuedunovel.cv.StoreCategoryBlockView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var category1: StoreCategoryBlockView
    private lateinit var category2: StoreCategoryBlockView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_store, container, false)
        category1 = view.findViewById(R.id.category1)
        category1.setContent(arrayListOf(
                ImageTextItem(1, "https://res1.xiaoqinre.com/images/cover/202012/1607930027BuZAoVwSA4N7f8iR.jpg",  "特搜组大吾 救国的橘色部队"),
                ImageTextItem(2, "https://res1.xiaoqinre.com/images/cover/202012/1607927806yEQ11SRIx0SfA6T3.jpg",  "神与地下城"),
                ImageTextItem(3, "https://res1.xiaoqinre.com/images/cover/202012/1607337790f6oI732I6aVyI416.jpg",  "悠米的玩偶"),
                ImageTextItem(4, "https://res1.xiaoqinre.com/images/cover/202012/1607591222jS3RgD6h6EjARjuz.jpg",  "纸箱战机"),
                ImageTextItem(5, "https://res1.xiaoqinre.com/images/cover/202012/1607337790f6oI732I6aVyI416.jpg",  "悠米的玩偶"),
                ImageTextItem(6, "https://res1.xiaoqinre.com/images/cover/202012/1607591222jS3RgD6h6EjARjuz.jpg",  "纸箱战机")
        ))

        category2 = view.findViewById(R.id.category2)
        category2.setContent(arrayListOf(
                ImageTextItem(7, "https://res1.xiaoqinre.com/images/cover/201807/1530537387O-NvKf3ZohGRjv6E.jpg",  "特搜组大吾"),
                ImageTextItem(8, "https://res1.xiaoqinre.com/images/cover/202003/1584710976ZTr6VbXMcLLMqpN_.jpg",  "学姐，不要直播出去！"),
                ImageTextItem(9, "https://res1.xiaoqinre.com/images/cover/201802/1519006021WbTCmS0j8HZbGmCl.jpg",  "悠米的玩偶"),
                ImageTextItem(10, "https://res1.xiaoqinre.com/images/cover/202012/1607591222jS3RgD6h6EjARjuz.jpg",  "纸箱战机"),
                ImageTextItem(11, "https://res1.xiaoqinre.com/images/cover/202012/1607337790f6oI732I6aVyI416.jpg",  "学姐，不要直播出去！"),
                ImageTextItem(12, "https://res1.xiaoqinre.com/images/cover/202008/1598511842VmNUod3GRindGqxP.jpg",  "纸箱战机")
        ))

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                StoreFragment().apply {
                    arguments = Bundle().apply {
                        // putString(ARG_PARAM1, param1)
                        // putString(ARG_PARAM2, param2)
                    }
                }
    }
}