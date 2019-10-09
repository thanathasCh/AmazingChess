package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.*
import kotlinx.android.synthetic.main.activity_create_room.*

class CreateRoomActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        rbBlueBoard.setOnClickListener {
            global_board = R.layout.activity_blue_board
        }

        rbClassicBoard.setOnClickListener {
            global_board = R.layout.activity_classic_board
        }

        rbVMSBoard.setOnClickListener {
            global_board = R.layout.activity_vms_board
        }

        tvPlayButton.setOnClickListener {
            createGameRoom(etNameRoom.text.toString())
        }
    }

    fun createGameRoom(roomName: String) {
        val key = db.GameRooms.push().key
        val match_key = db.Matches.push().key

        val Match = Match(
            GameRoomId = key!!,
            Move = Move()
        ).toMap()

        val GameRoom = GameRoomModel(
            Id = key,
            Name = roomName,
            StatusByte = 1
        ).toMap()

        val childUpdate = HashMap<String, Any>()
        val childMatch = HashMap<String, Any>()

        childUpdate["/$key"] = GameRoom
        childMatch["/$match_key"] = Match

        db.GameRooms.updateChildren(childUpdate)
        db.Matches.updateChildren(childMatch)

        val intent = Intent(this, Waiting::class.java)
        intent.putExtra("GAME_ROOM_ID", key)
        intent.putExtra("MATCH_ID", match_key)
        startActivity(intent)
    }
}
