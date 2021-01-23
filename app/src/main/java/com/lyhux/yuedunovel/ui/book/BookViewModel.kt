package com.lyhux.yuedunovel.ui.book

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.api.LoginParams
import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.db.BookshelfBean
import com.lyhux.yuedunovel.data.db.BookshelfDao
import com.lyhux.yuedunovel.data.http.ApiResponse
import com.lyhux.yuedunovel.data.http.BookDetailBean
import com.lyhux.yuedunovel.data.repository.BookshelfRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class BookViewModel : ViewModel(), KoinComponent {

    private val bookApi: BookApi by inject()

    val response: MutableLiveData<ApiResponse<BookDetailBean>> = MutableLiveData()
    val bookshelfLiveData: MutableLiveData<BookshelfBean?> = MutableLiveData()

    fun getDetail(bookId: String) {

        viewModelScope.launch {

            try {
                val result = bookApi.bookDetailAsync(
                        bookId
                ).await()

                Log.e(TAG, result.toString())

                if (result.isSuccess) {
                    response.postValue(result)
                } else {
                    response.postValue(result)
                }

            } catch (e: Exception){
                response.postValue(ApiResponse(0, e.message.toString(), null))
            }



        }
    }

    fun findByBookId(bookId: String) {
        GlobalScope.launch {
            val bookshelfBean = BookshelfRepository.findByBookId(bookId)
            bookshelfLiveData.postValue(bookshelfBean)
        }
    }

    fun addBookshelf(bean: BookshelfBean) {
        GlobalScope.launch {
            BookshelfRepository.insert(bean)
        }
    }

    companion object {
        const val TAG = "LoginViewModel";
    }
}