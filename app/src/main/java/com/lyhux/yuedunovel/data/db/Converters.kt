package com.lyhux.yuedunovel.data.db

import androidx.room.TypeConverter
import com.blankj.utilcode.util.ArrayUtils.toObject
import com.blankj.utilcode.util.GsonUtils
import com.lyhux.yuedunovel.data.BookBean

/**
 * @author Zsc
 * @date   2019/5/18
 * @desc
 */
class Converters {

    @TypeConverter
    fun list2String(list: List<String>?)
            : String = GsonUtils.toJson(list)

    // @TypeConverter
    // fun string2List(value: String?): List<String> {
    //     return GsonUtils.toList(value ?: "")
    // }
    //
    // @TypeConverter
    // fun string2RatingBean(value: String?): BookBean.RatingBean? {
    //     return GsonUtils.toObject(value ?: "")
    // }
    //
    // @TypeConverter
    // fun ratingBeanString(value: BookBean.RatingBean?): String? {
    //     return GsonUtils.toJson(value)
    // }
}