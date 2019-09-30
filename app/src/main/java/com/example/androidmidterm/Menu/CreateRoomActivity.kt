package com.example.androidmidterm.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.GameRoomModel
import kotlinx.android.synthetic.main.activity_create_room.*
import java.sql.Date

class CreateRoomActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        tvCreateButton.setOnClickListener {
            createGameRoom("user", etNameRoom.text.toString()) //TODO pass user Id
        }
    }

    fun createGameRoom(userId: String, roomName: String) {
        val key = db.GameRooms.push().key

        val GameRoom = GameRoomModel(
            Name = roomName,
            StatusByte = 1,
            CreatedBy = userId,
            CreatedAt = Date(System.currentTimeMillis()).toString()
        ).toMap()
        val childUpdate = HashMap<String, Any>()
        childUpdate["/GamesRooms/$key"] = GameRoom

        db.GameRooms.updateChildren(childUpdate)
    }
}
