package com.example.androidmidterm.Services

import java.sql.Date

data class User (
    var Id: Int,
    var Name: String,
    var FirstName: String,
    var LastName: String,
    var ImageURL: String,
    var CreatedAt: Date,
    var EditedAt: Date
)

data class GameRoom (
    var Id: Int,
    var Name: String,
    var StatusByte: Int,
    var Status: String = when(StatusByte) {
        0 -> "Ended"
        1 -> "Waiting"
        2 -> "Playing"
        else -> "Unknown"
    },
    var CreatedBy: Int,
    var CreatedAt: Date
)