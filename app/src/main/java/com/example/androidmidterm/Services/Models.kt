package com.example.androidmidterm.Services

import com.google.firebase.database.Exclude
import java.sql.Date

data class UserModel (
    var Id: String = "",
    var Name: String = "",
    var FirstName: String = "",
    var LastName: String = "",
    var Password: String = "",
    var Score: Int = 0
) {
    @Exclude
    fun toMap():Map<String, Any?> {
        return mapOf(
            "Id" to Id,
            "Name" to Name,
            "FirstName" to FirstName,
            "Password" to Password,
            "LastName" to LastName,
            "Score" to Score
        )
    }

    fun toViewModel() = UserViewModel (
        Id = Id,
        Name = Name,
        FullName = "${FirstName} ${LastName}",
        Score = Score
    )
}

data class GameRoomModel (
    var Id: String = "",
    var Name: String = "",
    var StatusByte: Int = 0
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Id" to Id,
            "Name" to Name,
            "StatusByte" to StatusByte
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

data class Match (
    var GameRoomId: String = "",
    var Move: Move = Move()
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "GameRoomId" to GameRoomId,
            "Move" to Move
        )
    }
}

data class Move (
    var oX: Int = 0,
    var oY: Int = 0,
    var dX: Int = 0,
    var dY: Int = 0
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "oX" to oX,
            "oY" to oY,
            "dX" to dX,
            "dY" to dY
        )
    }
}