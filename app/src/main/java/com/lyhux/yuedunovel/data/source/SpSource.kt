package com.lyhux.yuedunovel.data.source

import com.lyhux.yuedunovel.common.perfrence.SpAnyDelegates
import com.lyhux.yuedunovel.common.perfrence.SpDelegates
import com.lyhux.yuedunovel.data.UserBean


object SpSource {
    private const val SP_USER = "SP_USER"
    var user by SpAnyDelegates(SP_USER, UserBean::class.java)

    private const val SP_TOKEN = "SP_TOKEN"
    var token by SpDelegates(SP_TOKEN, "")

    private const val SP_THEME="SP_THEME"
    var appTheme by SpDelegates(SP_THEME, 0)
}