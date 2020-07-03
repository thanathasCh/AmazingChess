package com.example.amazing_chess.data.repositories

import android.content.Context
import com.example.amazing_chess.data.locals.UserSharedPreference
import com.example.amazing_chess.data.remotes.UserApi
import com.example.amazing_chess.models.dataModels.User

class UserRepository(private val context: Context) {
    private val userApi = UserApi()
    private val userSharedPreference = UserSharedPreference(context)

    fun login(userName: String, password: String, callback: (Boolean) -> Unit) {
        userApi.login(userName, password) {
            if (it) {
                userSharedPreference.login()
            } else {
                userSharedPreference.logout()
            }

            callback(it)
        }
    }

    fun logout() = userSharedPreference.logout()

    fun checkDuplicate(user: User, callback: (Boolean) -> Unit) = userApi.checkDuplicate(user, callback)

    fun createAccount(user: User, callback: (Boolean) -> Unit) = userApi.checkDuplicate(user, callback)
}