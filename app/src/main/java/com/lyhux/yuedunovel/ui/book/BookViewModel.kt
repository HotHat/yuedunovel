package com.lyhux.yuedunovel.ui.book

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.api.LoginParams
import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.http.ApiResponse
import com.lyhux.yuedunovel.data.http.BookDetailBean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class BookViewModel : ViewModel(), KoinComponent {

    private val bookApi: BookApi by inject()

    val response: MutableLiveData<ApiResponse<BookDetailBean>> = MutableLiveData()

    fun getDetail() {

        viewModelScope.launch {

            try {
                val result = bookApi.bookDetailAsync(
                        "1"
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

    companion object {
        const val TAG = "LoginViewModel";
    }
}