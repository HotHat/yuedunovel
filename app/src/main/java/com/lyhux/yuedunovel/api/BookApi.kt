package com.lyhux.yuedunovel.api

import com.google.gson.JsonObject
import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.UserBean
import com.lyhux.yuedunovel.data.http.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface BookApi {
    companion object {
        const val BASE_URL = "http://192.168.33.10"
    }

    @GET("/api/t1/{id}")
    fun t1(@Path("id") id: Int): Call<ApiResponse<JsonObject?>>?

    @GET("/api/t2/{id}")
    fun t2(@Path("id") id: Int): Call<ApiResponse<List<BookBean>?>>?

    @POST("/api/login")
    @FormUrlEncoded
    fun loginAsync(
        @Field("name") name: String,
        @Field("password") password: String
    ): Deferred<ApiResponse<UserBean>>

    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("/api/json")
    fun jsonAsync(@Body params: LoginParams ): Deferred<ApiResponse<UserBean>>

    // @GET("/api/login")
    // fun loginAsync(
    //         @Query("name") name: String,
    //         @Query("password") password: String
    // ): Deferred<ApiResponse<UserBean>>
}