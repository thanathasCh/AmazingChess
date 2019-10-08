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

        val GameRoom = GameRoomModel (
            Id = key!!,
            Name = roomName,
            StatusByte = 1,
            CreatedBy = userId,
            CreatedAt = Date(System.currentTimeMillis()).toString()
        ).toMap()

        val childUpdate = HashMap<String, Any>()
        childUpdate["/$key"] = GameRoom

        db.GameRooms.updateChildren(childUpdate)

        //TODO After create a room
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.rbClassicBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            //Intent to main game activity
                        }
                    }
                R.id.rbBlueBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            //Intent to main game activity
                        }
                    }
                R.id.rbVMSBoard ->
                    if(checked) {
                        tvPlayButton.setOnClickListener {
                            //Intent to main game activity
                        }
                    }
            }
        }
    }
}
