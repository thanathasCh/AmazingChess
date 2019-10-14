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

        val GameRoom = GameRoomModel(
            Id = key!!,
            Name = roomName,
            StatusByte = 1,
            Password = etPasswordRoom.text.toString().encrypt()
        ).toMap()

        val childUpdate = HashMap<String, Any>()

        childUpdate["/$key"] = GameRoom
        db.GameRooms.updateChildren(childUpdate)

        val intent = Intent(this, Waiting::class.java)
        GAME_ID = key
        intent.putExtra("GameRoomPass", etPasswordRoom.text.toString())
        intent.putExtra("GameRoomName", etNameRoom.text.toString())
        startActivity(intent)
    }
}
