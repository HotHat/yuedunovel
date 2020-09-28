package com.lyhux.yuedunovel.api

import com.google.gson.JsonObject
import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.UserBean
import com.lyhux.yuedunovel.data.http.ApiResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call

class MockBookApi : BookApi {
    override fun t1(id: Int): Call<ApiResponse<JsonObject?>>? {
        TODO("Not yet implemented")
    }

    override fun t2(id: Int): Call<ApiResponse<List<BookBean>?>>? {
        TODO("Not yet implemented")
    }

    override fun loginAsync(name: String, password: String): Deferred<ApiResponse<UserBean>> {
        return GlobalScope.async {
            // You have to wrap manually your result before return
            ApiResponse<UserBean>(1, "操作成功", UserBean("hello", "icon", "ttt"))
        }
    }

    override fun jsonAsync(params: LoginParams): Deferred<ApiResponse<UserBean>> {
        return GlobalScope.async {
            // You have to wrap manually your result before return
            ApiResponse<UserBean>(1, "操作成功", UserBean("world", "icon", "ttt"))
        }
    }
}