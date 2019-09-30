package com.example.androidmidterm.Services

import com.google.firebase.database.Exclude
import java.sql.Date

data class UserModel (
    var Id: String = "",
    var Name: String = "",
    var FirstName: String = "",
    var LastName: String = "",
    var ImageURL: String = "",
    var CreatedAt: String = "",
    var EditedAt: String = "",
    var Score: Int = 0
) {
    @Exclude
    fun toMap():Map<String, Any?> {
        return mapOf(
            "Name" to Name,
            "FirstName" to FirstName,
            "LastName" to LastName,
            "ImageURL" to ImageURL,
            "CreatedAt" to CreatedAt,
            "EditedAt" to EditedAt,
            "Score" to Score
        )
    }
}

data class GameRoomModel (
    var Id: String = "",
    var Name: String = "",
    var StatusByte: Int = 0,
    var CreatedBy: String = "",
    var CreatedAt: String = ""
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Name" to Name,
            "StatusByte" to StatusByte,
            "CreatedBy" to CreatedBy,
            "CreatedAt" to CreatedAt
        )
    }
}