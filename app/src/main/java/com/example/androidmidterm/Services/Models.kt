package com.example.androidmidterm.Services

import com.google.firebase.database.Exclude
import java.sql.Date

data class UserModel (
    var Id: String = "",
    var Name: String = "",
    var FirstName: String = "",
    var LastName: String = "",
    var Score: Int = 0,
    var ImageURL: String = "",
    var CreatedAt: String = "",
    var EditedAt: String = ""
) {
    @Exclude
    fun toMap():Map<String, Any?> {
        return mapOf(
            "Id" to Id,
            "Name" to Name,
            "FirstName" to FirstName,
            "LastName" to LastName,
            "Score" to Score,
            "ImageURL" to ImageURL,
            "CreatedAt" to CreatedAt,
            "EditedAt" to EditedAt
        )
    }

    fun toViewModel() = UserViewModel (
        Id = Id,
        Name = Name,
        FullName = "${FirstName} ${LastName}",
        Score = Score,
        ImageUrl = ImageURL
    )
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
            "Id" to Id,
            "Name" to Name,
            "StatusByte" to StatusByte,
            "CreatedBy" to CreatedBy,
            "CreatedAt" to CreatedAt
        )
    }

    fun toViewModel() = GameRoomViewModel (
        Id = Id,
        Name = Name,
        Status = when (StatusByte) {
            0 -> "Ended"
            1 -> "Waiting"
            2 -> "Playing"
            else -> "Unknown"
        }
    )
}