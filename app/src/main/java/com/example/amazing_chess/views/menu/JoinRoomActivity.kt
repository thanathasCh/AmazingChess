package com.example.amazing_chess.views.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.services.*
import com.example.amazing_chess.views.playboard.BlueBoard
import com.example.amazing_chess.views.playboard.ClassicBoard
import com.example.amazing_chess.views.playboard.VmsBoard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_join_room.*

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
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.children.iterator().next().child("Password").value.toString() == etPasswordJoinRoom.text.toString().encrypt()) {
                    if (snapshot.children.iterator().next().child("StatusByte").value.toString().toInt() == 1) {
                        val gameRoom = GameRoomModel()

                        snapshot.children.forEach {
                            gameRoom.Id = it.child("Id").value.toString()
                            gameRoom.Name = it.child("Name").value.toString()
                            gameRoom.StatusByte = it.child("StatusByte").value.toString().toInt()
                        }
                        db.GameRooms.child(gameRoom.Id).child("StatusByte").setValue(2)
                        val intent = when (global_board) {
                            R.layout.activity_classic_board -> Intent(
                                this@JoinRoomActivity,
                                ClassicBoard::class.java
                            )
                            R.layout.activity_blue_board -> Intent(
                                this@JoinRoomActivity,
                                BlueBoard::class.java
                            )
                            R.layout.activity_vms_board -> Intent(
                                this@JoinRoomActivity,
                                VmsBoard::class.java
                            )
                            else -> Intent(this@JoinRoomActivity, ClassicBoard::class.java)
                        }
                        GAME_ID = gameRoom.Id
                        global_player_status = 1
                        my_turn = false
                        startActivity(intent)
                        finish()
                    } else {
                        warningBox(this@JoinRoomActivity, "Room is full",
                            "This room is full, please select new room")
                    }
                } else {
                    warningBox(this@JoinRoomActivity, "Room Not Found",
                        "Please check your room name and password again.")
                }
            }
        })
    }
}
