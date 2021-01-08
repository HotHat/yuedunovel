package com.lyhux.yuedunovel.ui.account

import android.content.Context
import android.content.Intent
import android.database.DataSetObserver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.lyhux.yuedunovel.R
import com.lyhux.yuedunovel.ui.login.LoginActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var listView: ListView

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
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        listView = view.findViewById(R.id.setting_list)

        val items = arrayListOf<SettingItem>(
                SettingItem(R.drawable.ic_bookshelf_search, "福利中心") { it ->
                    Toast.makeText(it, "AAAA", Toast.LENGTH_SHORT).show()
                },
                SettingItem(R.drawable.ic_bookshelf_search, "消息中心"){ it ->
                    Toast.makeText(it, "BBB", Toast.LENGTH_SHORT).show()
                },
                SettingItem(R.drawable.ic_bookshelf_search, "充值记录"){ it ->
                    Toast.makeText(it, "CCC", Toast.LENGTH_SHORT).show()
                },
                SettingItem(R.drawable.ic_bookshelf_search, "订阅记录"){ it ->
                    Toast.makeText(it, "DDD", Toast.LENGTH_SHORT).show()
                },
                SettingItem(R.drawable.ic_bookshelf_search, "我读过的书"){ it ->
                    Toast.makeText(it, "EEEE", Toast.LENGTH_SHORT).show()
                }
        )

        listView.adapter = MyListAdapter(this.requireActivity(), items)

        val loginView = view.findViewById<TextView>(R.id.login_view)
        loginView.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)

            startActivity(intent)
        }

        return view;
    }


    data class SettingItem (val imageId: Int, val text: String, val action:(content: Context)->Unit)

    class MyListAdapter: ListAdapter {
        private var context: Context
        private var arrayList: ArrayList<SettingItem>

        constructor(context: Context, arrayList: ArrayList<SettingItem>) {
            this.arrayList = arrayList;
            this.context = context;
        }

        override fun isEmpty(): Boolean {
            return false
        }

        override fun getView(pos: Int, contentView: View?, parent: ViewGroup?): View {
            val item = arrayList[pos]

            if (contentView == null) {
                val view = LayoutInflater.from(context).inflate(R.layout.setting_list_item, null)
                view.setOnClickListener(View.OnClickListener {
                    item.action(context)
                })
                val title: TextView = view.findViewById(R.id.setting_item_text)
                val img: ImageView = view.findViewById(R.id.setting_item_image)
                title.text = item.text
                img.setImageResource(item.imageId)

                return view
            }

            return  contentView
        }

        override fun registerDataSetObserver(p0: DataSetObserver?) {
        }

        override fun getItemViewType(p0: Int): Int {
            return  1;
        }

        override fun getItem(p0: Int): Any {
            return arrayList[p0]
        }

        override fun getViewTypeCount(): Int {
            return 1;
        }

        override fun isEnabled(p0: Int): Boolean {
            return true
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        override fun areAllItemsEnabled(): Boolean {
            return false
        }

        override fun unregisterDataSetObserver(p0: DataSetObserver?) {

        }

        override fun getCount(): Int {
            return arrayList.size
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                AccountFragment().apply {
                    arguments = Bundle().apply {
                        // putString(ARG_PARAM1, param1)
                        // putString(ARG_PARAM2, param2)
                    }
                }
    }
}