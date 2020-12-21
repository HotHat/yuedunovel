package com.lyhux.yuedunovel.ui.library

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyhux.yuedunovel.api.BookApi
import com.lyhux.yuedunovel.data.http.ApiResponse
import com.lyhux.yuedunovel.data.http.LibraryBean
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback

class LibraryFragmentModel : ViewModel(), KoinComponent {

    private val bookApi: BookApi by inject()

//    val name: ObservableField<String> = ObservableField()
//    val password: ObservableField<String> = ObservableField()

    val response: MutableLiveData<ApiResponse<LibraryBean>> = MutableLiveData()

    fun getLibraryPortal() {
        viewModelScope.launch {

            try {
                val result = bookApi.libraryPortalAsync().await()

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
        const val TAG = "LibraryFragmentModel";
    }
}