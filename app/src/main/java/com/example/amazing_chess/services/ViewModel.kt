package com.example.amazing_chess.services

data class UserViewModel (
    var Id: String,
    var Name: String,
    var FullName: String,
    var Score: Int
)

data class GameRoomViewModel (
    var Id: String,
    var Name: String,
    var Status: String
)