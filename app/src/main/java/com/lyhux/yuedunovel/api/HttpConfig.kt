package com.lyhux.yuedunovel.api

import android.util.Log
import com.lyhux.yuedunovel.data.source.SpSource
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import java.net.URLDecoder

object HttpConfig {
    /**
     * 描述： 设置token
     */
    fun tokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = configRequestBuilder(chain)
            return@Interceptor chain.proceed(request.build())
        }
    }

    /**
     * 配置请求信息
     */
    private fun configRequestBuilder(chain: Interceptor.Chain): Request.Builder {
        var request = chain.request()
        if (request.method() == "GET") {

            Log.e("HTTP_CONFIG GET", request.url().query().toString())
            val url = request.url().newBuilder()
                    .addQueryParameter("terminal", "Android")
                    .addQueryParameter("token", "android-token")
                    .build()

            return request.newBuilder().url(url)

        } else if (request.method() == "POST") {

            if (request.body() is FormBody) {
                val bodyBuilder = FormBody.Builder()
                var formBody = request.body() as FormBody

                //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
                for (i in 0 until formBody.size()) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i))
                }

                formBody = bodyBuilder
                        .addEncoded("terminal", "Android")
                        .addEncoded("ver", "1.2.3")
                        .addEncoded("time", System.currentTimeMillis().toString())
                        .build()

                val bodyMap: MutableMap<String, String> = HashMap()
                val nameList: MutableList<String> = ArrayList()

                for (i in 0 until formBody.size()) {
                    nameList.add(formBody.encodedName(i))
                    bodyMap[formBody.encodedName(i)] = URLDecoder.decode(formBody.encodedValue(i), "UTF-8")
                }


                val builder = StringBuilder()
                for (i in nameList.indices) {
                    builder.append("&").append(nameList[i]).append("=")
                            .append(URLDecoder.decode(bodyMap[nameList[i]], "UTF-8"))
                }

                formBody = bodyBuilder.addEncoded("sign", "abc")
                        .build()
               return request.newBuilder().post(formBody)
            } else if (request.body()?.contentType() == MediaType.get("application/json; charset=UTF-8")) {

                val b = request.body()

                Log.e("HTTP_CONFIG JSON", b.toString())
            }

            Log.e("HTTP_CONFIG POST", request.body().toString())
            Log.e("HTTP_CONFIG POST", request.body()?.contentType().toString())
        }

        return chain.request()
                .newBuilder()
                .addHeader("terminal","Android")
                .addHeader("access-token", SpSource.token)

    }
}