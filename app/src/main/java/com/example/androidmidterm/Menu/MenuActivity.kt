package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.R
import com.example.androidmidterm.RankingActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tvCreateRoom.setOnClickListener {
            var createRoom = Intent(this, CreateRoomActivity::class.java)
            startActivity(createRoom)
        }

        tvJoinRoom.setOnClickListener {
            var joinRoom = Intent(this, JoinRoomActivity::class.java)
            startActivity(joinRoom)
        }

        tvRanking.setOnClickListener {
            var rank = Intent(this, RankingActivity::class.java)
            startActivity(rank)
        }
    }
}
