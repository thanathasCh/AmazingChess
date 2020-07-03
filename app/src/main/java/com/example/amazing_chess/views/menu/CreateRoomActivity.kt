package com.example.amazing_chess.views.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.services.*
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

        val GameRoom = GameRoomModel(
            Id = key!!,
            Name = roomName,
            StatusByte = 1,
            Password = etPasswordRoom.text.toString().encrypt()
        ).toMap()

        val childUpdate = HashMap<String, Any>()

        childUpdate["/$key"] = GameRoom
        db.GameRooms.updateChildren(childUpdate)

        val intent = Intent(this, WaitingActivity::class.java)
        GAME_ID = key
        intent.putExtra("GameRoomPass", etPasswordRoom.text.toString())
        intent.putExtra("GameRoomName", etNameRoom.text.toString())
        startActivity(intent)
        finish()
    }
}
