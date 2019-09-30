package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.MainActivity
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.DbContext
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_join_room.*

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
        data.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children.iterator().next().child("Name").value.toString()
                if (snapshot.exists() && data == roomName) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.putExtra("roomId", data)
                    startActivity(intent)
                } else {
                    //TODO Room not found.
                }
            }

        })
    }
}
