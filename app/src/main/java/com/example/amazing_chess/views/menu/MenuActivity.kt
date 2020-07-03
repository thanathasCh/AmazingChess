package com.example.amazing_chess.views.menu

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.views.leaderBoard.LeaderBoardActivity
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
            val rank = Intent(this, LeaderBoardActivity::class.java)
            startActivity(rank)
        }

    }

    fun playMusic(){
        mp = MediaPlayer.create(this, R.raw.chopin_ballade_no1_in_g_minor)
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
