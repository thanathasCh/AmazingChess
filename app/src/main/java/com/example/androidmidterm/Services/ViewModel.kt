package com.example.androidmidterm.Services

data class UserViewModel (
    var Id: String,
    var Name: String,
    var FullName: String,
    var ImageUrl: String
)

data class GameRoomViewModel (
    var Id: String,
    var Name: String,
    var Status: String
)