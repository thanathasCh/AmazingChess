package com.example.androidmidterm.Services

import com.google.firebase.database.FirebaseDatabase

class DbContext {
    val Users = FirebaseDatabase.getInstance().getReference("Users")
    val GameRooms = FirebaseDatabase.getInstance().getReference("GameRooms")
}