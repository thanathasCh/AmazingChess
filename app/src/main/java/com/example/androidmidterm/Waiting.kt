package com.example.androidmidterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.GameRoomModel
import com.example.androidmidterm.Services.global_board
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_waiting.*

class Waiting : AppCompatActivity() {

    val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)
        val data = db.GameRooms.child(intent.getStringExtra("GAMEROOM_ID"))

        data.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) { }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val gameRoom = dataSnapshot.getValue(GameRoomModel::class.java)

                Key.text = gameRoom?.Name

                if (gameRoom?.StatusByte == 2) {
                    val intent = when(global_board) {
                        R.layout.activity_classic_board -> Intent(this@Waiting, ClassicBoard::class.java)
                        R.layout.activity_blue_board -> Intent(this@Waiting, BlueBoard::class.java)
                        R.layout.activity_vms_board -> Intent(this@Waiting, VmsBoard::class.java)
                        else -> Intent(this@Waiting, ClassicBoard::class.java)
                    }
                    intent.putExtra("MATCH_ID", intent.getStringExtra("MATCH_ID"))
                    intent.putExtra("STATUS", 0)
                    startActivity(intent)
                }
            }

        })
    }
}
