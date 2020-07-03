package com.example.amazing_chess.services

import com.google.firebase.database.FirebaseDatabase

class DbContext {
    val Users = FirebaseDatabase.getInstance().getReference("Users")
    val GameRooms = FirebaseDatabase.getInstance().getReference("GameRooms")
}