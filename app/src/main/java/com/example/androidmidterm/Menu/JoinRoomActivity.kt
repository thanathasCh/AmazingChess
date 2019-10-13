package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.*
import com.example.androidmidterm.PlayBoard.BlueBoard
import com.example.androidmidterm.PlayBoard.ClassicBoard
import com.example.androidmidterm.PlayBoard.VmsBoard
import com.example.androidmidterm.Services.*
import kotlinx.android.synthetic.main.activity_join_room.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class JoinRoomActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_room)

        rbClassicBoard.setOnClickListener {
            global_board = R.layout.activity_classic_board
        }

        rbBlueBoard.setOnClickListener {
            global_board = R.layout.activity_blue_board
        }

        rbVMSBoard.setOnClickListener {
            global_board = R.layout.activity_vms_board
        }

        tvJoin.setOnClickListener {
            joinRoom(etCode.text.toString())
        }
    }

    fun joinRoom(roomName: String) {
        val data = db.GameRooms.orderByChild("Name").equalTo(roomName)
        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) { }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gameRoom = GameRoomModel()

                    snapshot.children.forEach {
                        gameRoom.Id = it.child("Id").value.toString()
                        gameRoom.Name = it.child("Name").value.toString()
                        gameRoom.StatusByte = it.child("StatusByte").value.toString().toInt()
                    }
                    db.GameRooms.child(gameRoom.Id).child("StatusByte").setValue(2)
                    val intent = when(global_board) {
                        R.layout.activity_classic_board -> Intent(this@JoinRoomActivity, ClassicBoard::class.java)
                        R.layout.activity_blue_board -> Intent(this@JoinRoomActivity, BlueBoard::class.java)
                        R.layout.activity_vms_board -> Intent(this@JoinRoomActivity, VmsBoard::class.java)
                        else -> Intent(this@JoinRoomActivity, ClassicBoard::class.java)
                    }
                    intent.putExtra("MATCH_ID", gameRoom.Id)
                    global_player_status = 1
                    startActivity(intent)
                } else {
                    warningBox(this@JoinRoomActivity, "Room Not Found",
                        "Please check your room name again.")
                }
            }
        })
    }
}
