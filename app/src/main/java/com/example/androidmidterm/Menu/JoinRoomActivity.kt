package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidmidterm.MainActivity
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_join_room.*
import kotlinx.android.synthetic.main.activity_create_room.*
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.GameRoomModel
import com.example.androidmidterm.chess
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class JoinRoomActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_room)

        tvJoin.setOnClickListener {
            joinRoom(etCode.text.toString())
        }
    }

    fun joinRoom(roomName: String) {
        val data = db.GameRooms.orderByChild("Name").equalTo(roomName)
        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val gameRoom = snapshot.getValue(GameRoomModel::class.java)
//                    val ref = db.
//                    gameRoom!!.StatusByte = 2
//                    db.GameRooms.updateChildren()
                    val intent = Intent(this@JoinRoomActivity, chess::class.java)
                    intent.putExtra("gameRoomId", gameRoom?.Id)
                    intent.putExtra("STATUS", 1)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@JoinRoomActivity, "Room Not Found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
