package com.example.amazing_chess.views.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amazing_chess.R
import com.example.amazing_chess.services.*
import com.example.amazing_chess.views.playboard.BlueBoard
import com.example.amazing_chess.views.playboard.ClassicBoard
import com.example.amazing_chess.views.playboard.VmsBoard
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.activity_waiting.*
import java.lang.Exception

class WaitingActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting)
        Key.text = intent.getStringExtra("GameRoomName")
        keyPassword.text = intent.getStringExtra("GameRoomPass")
        val data = db.GameRooms.child(GAME_ID)

        data.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                try {
                    val v = dataSnapshot.getValue(Int::class.java)
                    if (v == 2) {
                        val intent = when(global_board) {
                            R.layout.activity_classic_board -> Intent(this@WaitingActivity, ClassicBoard::class.java)
                            R.layout.activity_blue_board -> Intent(this@WaitingActivity, BlueBoard::class.java)
                            R.layout.activity_vms_board -> Intent(this@WaitingActivity, VmsBoard::class.java)
                            else -> Intent(this@WaitingActivity, ClassicBoard::class.java)
                        }
                        my_turn = true
                        global_player_status = 0
                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {}
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {}

            override fun onChildRemoved(p0: DataSnapshot) {}

        })
    }
}
