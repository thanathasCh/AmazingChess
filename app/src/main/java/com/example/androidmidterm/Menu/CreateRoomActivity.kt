package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.androidmidterm.R
import java.sql.Date
import com.example.androidmidterm.Services.DbContext
import kotlinx.android.synthetic.main.activity_create_room.*
import com.example.androidmidterm.Services.GameRoomModel
import com.example.androidmidterm.Services.Match
import com.example.androidmidterm.Services.Move
import com.example.androidmidterm.Waiting

class CreateRoomActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        tvPlayButton.setOnClickListener {
            createGameRoom("user", etNameRoom.text.toString())
        }
    }

    fun createGameRoom(userId: String, roomName: String) {
        val key = db.GameRooms.push().key
        val match_key = db.Matches.push().key

        val Match = Match (
            GameRoomId = key!!,
            Move = Move()
        ).toMap()

        val Move = Move().toMap()

        val GameRoom = GameRoomModel (
            Id = key,
            Name = roomName,
            StatusByte = 1,
            CreatedBy = userId,
            CreatedAt = Date(System.currentTimeMillis()).toString()
        ).toMap()

        val childUpdate = HashMap<String, Any>()
        val childMatch = HashMap<String, Any>()

        childUpdate["/$key"] = GameRoom
        childMatch["/$match_key"] = Match

        db.GameRooms.updateChildren(childUpdate)
        db.Matches.updateChildren(childMatch)

        val intent = Intent(this, Waiting::class.java)
        intent.putExtra("GAMEROOM_ID", key)
        intent.putExtra("MATCH_ID", match_key)
        startActivity(intent)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.rbClassicBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            var intent = Intent(this, Waiting::class.java)
                            startActivity(intent)
                        }
                    }
                R.id.rbBlueBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            tvPlayButton.setOnClickListener {
                                var intent = Intent(this, Waiting::class.java)
                                startActivity(intent)
                            }                        }
                    }
                R.id.rbVMSBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            tvPlayButton.setOnClickListener {
                                var intent = Intent(this, Waiting::class.java)
                                startActivity(intent)
                            }                        }
                    }
            }
        }
    }
}
