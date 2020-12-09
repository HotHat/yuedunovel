package com.lyhux.yuedunovel.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lyhux.yuedunovel.util.hmacsha256
import okhttp3.*
import okio.Buffer
import java.util.*
import kotlin.collections.HashMap

class SignInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (request.method() == "GET") {
            print("It's GET Request")
            val params = request.url().queryParameterNames()
            val map = HashMap<String, String>()
            map["terminal"] = "android"
            map["time"] = (System.currentTimeMillis() / 1000).toInt().toString()
            for (p in params) {
                map[p] = request.url().queryParameter(p) ?: ""
            }

            val sign = this.signStringBuild(map).hmacsha256(this.getKey())

            map["sign"] = sign

            val query = queryBuild(map)
            val newUrl = request.url().newBuilder().query(query).build()

            var newRequest = request.newBuilder().url(newUrl).build()

            return chain.proceed(newRequest)

        } else if (request.method().toUpperCase() == "POST") {
            val requestBody = request.body() as RequestBody
            if (requestBody.contentType() ==  MediaType.get("application/json; charset=UTF-8")) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                val oldParamsJson: String = buffer.readUtf8()
                val gson = Gson()
                val type = object: TypeToken<HashMap<String, String>>(){}.type
                var map: HashMap<String, String> = gson.fromJson(oldParamsJson, type) //原始参数
                map["terminal"] = "android"
                map["time"] = (System.currentTimeMillis() / 1000).toInt().toString()

                map["sign"] = this.signStringBuild(map).hmacsha256(this.getKey()) //重新组装

                val newJsonParams: String = gson.toJson(map) //装换成json字符串
                val builder = request.newBuilder().post(RequestBody.create(requestBody.contentType(), newJsonParams));
                builder.header("Content-Length", newJsonParams.toByteArray().size.toString())

                return chain.proceed(builder.build())
            }
        }

        return chain.proceed(request)
    }

    private fun getKey() : String {
        return "123456"
    }

    private fun signStringBuild(map: HashMap<String, String>) : String {
        val keys = TreeSet(map.keys)
        val paramsStr = StringBuilder()
        for (key in keys) {
            val v = map[key].toString()
            if (v != "") {
                paramsStr.append("$key=$v&")
            }
        }
        val result = paramsStr.toString()
        return result.trimEnd('&')
    }

    private fun postSignStringBuild(map: HashMap<String, Any>) : String {
        val keys = TreeSet(map.keys)
        val paramsStr = StringBuilder()
        for (key in keys) {
            val v = map[key].toString()
            paramsStr.append("$key=$v&")
        }
        val result = paramsStr.toString()
        return result.trimEnd('&')
    }



    private fun queryBuild(map: HashMap<String, String>): String {
        var paramsStr = StringBuilder()
        for (k in map.keys) {
            val v = map[k]
            paramsStr.append("$k=$v&")
        }
        val result = paramsStr.toString()
        return result.trimEnd('&')
    }
}
