package com.example.androidmidterm.Menu

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidmidterm.Menu.Ranking.RankingActivity
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        playMusic()

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

    fun playMusic(){
        mp = MediaPlayer.create(this, R.raw.hail_to_the_king)
        mp.start()
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)

        ivPlay.setOnClickListener {
            if (mp.isPlaying) {
                mp.pause()
                ivPlay.setBackgroundResource(R.drawable.ic_action_stop)
            } else {
                mp.start()
                ivPlay.setBackgroundResource(R.drawable.ic_action_play)
            }
        }
    }
}
