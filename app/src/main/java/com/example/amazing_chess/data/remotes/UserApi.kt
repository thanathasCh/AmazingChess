package com.example.amazing_chess.data.remotes

import com.example.amazing_chess.models.UrlDict
import com.example.amazing_chess.models.dataModels.User
import com.example.amazing_chess.services.fromJson
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson

class UserApi {
    private val url_dict = UrlDict()
    private val mGson = Gson()

    fun login(userName: String, password: String, callback: (Boolean) -> Unit) {
        val requestJson = User (
            userName = userName,
            password = password
        ).toJson()

        Fuel.post(url_dict.login)
            .jsonBody(requestJson)
            .responseString { request, response, result ->
                if (response.statusCode == 200) {
                    callback(mGson.fromJson<Boolean>(result.component1() ?: "false"))
                } else {
                    callback(false)
                }
            }
    }

    fun checkDuplicate(user: User, callback: (Boolean) -> Unit) {
        Fuel.post(url_dict.checkDuplicate)
            .jsonBody(user.toJson())
            .responseString { request, response, result ->
                if (response.statusCode == 200) {
                    callback(mGson.fromJson<Boolean>(result.component1() ?: "false"))
                } else {
                    callback(false)
                }
            }
    }

    fun createAccount(user: User, callback: (Boolean) -> Unit) {
        Fuel.post(url_dict.createAccount)
            .jsonBody(user.toJson())
            .responseString { request, response, result ->
                if (response.statusCode == 200) {
                    callback(mGson.fromJson<Boolean>(result.component1() ?: "false"))
                } else {
                    callback(false)
                }
            }
    }
}