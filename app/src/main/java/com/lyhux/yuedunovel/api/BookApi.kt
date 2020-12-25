package com.lyhux.yuedunovel.api

import com.lyhux.yuedunovel.data.BookBean
import com.lyhux.yuedunovel.data.http.ChapterItemBean
import com.lyhux.yuedunovel.data.UserBean
import com.lyhux.yuedunovel.data.http.ApiResponse
import com.lyhux.yuedunovel.data.http.BookDetailBean
import com.lyhux.yuedunovel.data.http.LibraryBean
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface BookApi {
    companion object {
        const val BASE_URL = "http://192.168.33.10"
    }

    @GET("/api/v1/book-detail?")
    fun bookDetailAsync(@Query("book_id") bookId: String): Deferred<ApiResponse<BookDetailBean>>

    @GET("/api/v1/library-portal")
    fun libraryPortalAsync(): Deferred<ApiResponse<LibraryBean>>

    // 章节内容列表
    @GET("api/v1/book-chapter-list")
    fun bookChapterListAsync(@Query("book_id") bookId: String): Deferred<ApiResponse<List<String>>>

    // 章节内容
    @GET("/api/v1/book-chapter-detail")
    fun bookChapterAsync(@Query("book_id") bookId: String, @Query("chapter_id") chapterId: String):
            Deferred<ApiResponse<ChapterItemBean>>


    // below api only for test

    @GET("/api/book/category/{id}/{page}")
    fun categoryAsync(
        @Path("id") id: String,
        @Path("page") page: Int
    ): Deferred<ApiResponse<List<BookBean>>>



    // 章节内容测试用
    @GET("/api/book/chapter/{chapterId}")
    fun bookChapterAsync2(@Path("url") url: String):
            Deferred<ChapterItemBean>

    @Headers("Content-Type:application/json; charset=UTF-8")
    @POST("/api/json")
    fun jsonAsync(@Body params: LoginParams ): Deferred<ApiResponse<UserBean>>


    @POST("/api/login")
    @FormUrlEncoded
    fun loginAsync(
            @Field("name") name: String,
            @Field("password") password: String
    ): Deferred<ApiResponse<UserBean>>



}