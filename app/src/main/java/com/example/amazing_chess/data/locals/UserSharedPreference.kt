package com.example.amazing_chess.data.locals

import android.content.Context

class UserSharedPreference(private val context: Context) {
    private val userPreference = "user"

    private val isLogin = "isLogin"
    private val information = "information"

    fun login() {
        with(context.getSharedPreferences(userPreference, Context.MODE_PRIVATE).edit()) {
            putBoolean(isLogin, true)
            apply()
        }
    }

    fun logout() {
        with(context.getSharedPreferences(userPreference, Context.MODE_PRIVATE).edit()) {
            putBoolean(isLogin, false)
            apply()
        }
    }

    fun isLogin(): Boolean {
        return context.getSharedPreferences(userPreference, Context.MODE_PRIVATE)
            .getBoolean(isLogin, false)
    }
}