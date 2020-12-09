package com.lyhux.yuedunovel.util

import java.util.*
import kotlin.collections.HashMap

fun getSignStringBuild(map: HashMap<String, String>) : String {
    val keys = TreeSet(map.keys)
    val paramsStr = StringBuilder()
    for (key in keys) {
        val v = map[key].toString()
        paramsStr.append("$key=$v&")
    }
    val result = paramsStr.toString()
    return result.trimEnd('&')
}

fun postSignStringBuild(map: HashMap<String, Any>) : String {
    val keys = TreeSet(map.keys)
    val paramsStr = StringBuilder()
    for (key in keys) {
        val v = map[key].toString()
        paramsStr.append("$key=$v&")
    }
    val result = paramsStr.toString()
    return result.trimEnd('&')
}



fun queryBuild(map: HashMap<String, String>): String {
    var paramsStr = StringBuilder()
    for (k in map.keys) {
        val v = map[k]
        paramsStr.append("$k=$v&")
    }
    val result = paramsStr.toString()
    return result.trimEnd('&')
}

fun bytesToHex(bytes: ByteArray): String {
    val hexArray = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f'
    )
    val hexChars = CharArray(bytes.size * 2)
    var v: Int

    for (j in bytes.indices) {
        println(bytes[j].toUInt())
        v = bytes[j].toInt() and 0xFF
        hexChars[j * 2] = hexArray[v ushr 4]
        hexChars[j * 2 + 1] = hexArray[v and 0x0F]
    }
    return String(hexChars)
}