package com.example.amazing_chess.models.dataModels

data class LeaderBoard (
    var userId: Long = 0L,
    var userName: String = "",
    var fullName: String = "",
    var score: Int = 0,
    var isUser: Boolean = false
)