package com.example.amazing_chess.models.dataModels

import org.json.JSONObject
import java.sql.Date

data class User (
    var id: Long = 0L,
    var userName: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var password: String = "",
    var email: String = "",
    var createdAt: Date = Date(System.currentTimeMillis())
) {
    fun toJson(): String {
        val json = JSONObject()

        with (json) {
            put("id", id)
            put("userName", userName)
            put("firstName", firstName)
            put("lastName", lastName)
            put("pasword", password)
            put("email", email)
            put("createdAt", createdAt)
        }

        return json.toString()
    }
}