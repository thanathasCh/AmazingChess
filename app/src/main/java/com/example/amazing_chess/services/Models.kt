package com.example.amazing_chess.services

import com.google.firebase.database.Exclude

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
    var StatusByte: Int = 0,
    var Password: String = "",
    var Move1: Move =  Move(),
    var Move2: Move = Move()
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Id" to Id,
            "Name" to Name,
            "StatusByte" to StatusByte,
            "Password" to Password,
            "Move1" to Move1,
            "Move2" to Move2
        )
    }
}

data class Move (
    var Value: String = "0,0,0,0"
)
