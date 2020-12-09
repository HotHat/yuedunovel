package com.lyhux.yuedunovel.util

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


fun String.hmacsha256(secret: String): String {

    val sha256 = Mac.getInstance("HmacSHA256")
    val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
    sha256.init(secretKey)
    val thisByte = this.toByteArray()
    val byte = sha256.doFinal(thisByte)
    return bytesToHex(byte).toLowerCase() // 重点
}
