package com.lyhux.yuedunovel.common.perfrence

import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson
import kotlin.reflect.KProperty

class SpAnyDelegates<T>(
        private val key: String,
        private val clazz: Class<T>
) {

    companion object {
        private val gson by lazy { Gson() }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return gson.fromJson(SPUtils.getInstance().getString(key), clazz)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        SPUtils.getInstance().put(key, gson.toJson(value))
    }
}