package com.lyhux.yuedunovel.ui.library

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.cv.ImageTextItem
import com.lyhux.yuedunovel.cv.StoreCategoryBlockView
import com.stx.xhb.androidx.XBanner
import com.stx.xhb.androidx.entity.BaseBannerInfo
import org.koin.androidx.viewmodel.ext.android.viewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {
    private lateinit var category1: StoreCategoryBlockView
    private lateinit var category2: StoreCategoryBlockView
    private lateinit var category3: StoreCategoryBlockView
    private lateinit var mXBanner : XBanner

    private val libraryFragmentModel by viewModel<LibraryFragmentModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // param1 = it.getString(ARG_PARAM1)
            // param2 = it.getString(ARG_PARAM2)
        }

        libraryFragmentModel.getLibraryPortal()

        libraryFragmentModel.response.observe(this, Observer {
            Log.e(TAG, it.isSuccess.toString() + it.code)
            it.doSuccess { it ->
                // showToast(it.token)
                Log.e(TAG, "success")


                // 轮播图
                var carouseList = it.carousel.map { item -> object:BaseBannerInfo{
                    override fun getXBannerUrl(): Any {
                        return item.image
                    }

                    override fun getXBannerTitle(): String {
                        return item.title
                    }
                }}
                mXBanner.setBannerData(carouseList)

                // 分类

                // 分块

                when (it.block.size) {
                    0 -> {
                        category1.visibility = View.GONE
                        category2.visibility = View.GONE
                        category3.visibility = View.GONE
                    }
                    1 -> {
                        category2.visibility = View.GONE
                        category3.visibility = View.GONE

                        val b = it.block[0]
                        category1.setMoreCategory(b.category)

                        val items = b.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category1.setContent(items)

                    }

                    2 -> {
                        category3.visibility = View.GONE

                        val b1 = it.block[0]
                        category1.setMoreCategory(b1.category)

                        val items1 = b1.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category1.setContent(items1)

                        val b2 = it.block[1]
                        category2.setMoreCategory(b2.category)

                        val items2 = b2.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category2.setContent(items2)
                    }


                    else -> {
                        val b1 = it.block[0]
                        category1.setMoreCategory(b1.category)

                        val items1 = b1.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category1.setContent(items1)

                        val b2 = it.block[1]
                        category2.setMoreCategory(b2.category)

                        val items2 = b2.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category2.setContent(items2)

                        val b3 = it.block[2]
                        category3.setMoreCategory(b3.category)

                        val items3 = b3.items.map { item ->
                            ImageTextItem(item.bookId, item.cover, item.title)
                        }

                        category3.setContent(items3)
                    }
                }

            }.doError {
                // showToast(it.message)
                Log.e(TAG, "failure")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_store, container, false)
        category1 = view.findViewById(R.id.category1)
        category2 = view.findViewById(R.id.category2)
        category3 = view.findViewById(R.id.category3)


        mXBanner = view.findViewById(R.id.xbanner)
        // mXBanner.loadImage(XBanner.XBannerAdapter())


        mXBanner.loadImage { banner, model, view, position ->
            //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
            //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
            Glide.with(this.requireActivity()).load((model as BaseBannerInfo).xBannerUrl).placeholder(R.drawable.ic_bookshelf_edit).error(R.drawable.ic_bookshelf_edit).into(view as ImageView)
        }

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
                LibraryFragment().apply {
                    arguments = Bundle().apply {
                        // putString(ARG_PARAM1, param1)
                        // putString(ARG_PARAM2, param2)
                    }
                }
        const val TAG = "LibraryFragment"
    }
}