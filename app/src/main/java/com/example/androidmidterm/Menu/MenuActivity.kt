package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.Menu.Ranking.RankingActivity
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        tvCreateRoom.setOnClickListener {
            val createRoom = Intent(this, CreateRoomActivity::class.java)
            startActivity(createRoom)
        }

        tvJoinRoom.setOnClickListener {
            val joinRoom = Intent(this, JoinRoomActivity::class.java)
            startActivity(joinRoom)
        }

        tvRanking.setOnClickListener {
            val rank = Intent(this, RankingActivity::class.java)
            startActivity(rank)
        }
    }
}
