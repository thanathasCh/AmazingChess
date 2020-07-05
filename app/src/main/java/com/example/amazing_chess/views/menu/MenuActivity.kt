package com.example.amazing_chess.views.menu

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.data.repositories.UserRepository
import com.example.amazing_chess.services.AlertDialogBuilder
import com.example.amazing_chess.views.leaderBoard.LeaderBoardActivity
import com.example.amazing_chess.views.login.LoginActivity
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    private lateinit var mp: MediaPlayer
    private val alertDialogBuilder = AlertDialogBuilder(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        playMusic()

        cvCreate.setOnClickListener {
            val createRoom = Intent(this, CreateRoomActivity::class.java)
            startActivity(createRoom)
        }

        cvJoin.setOnClickListener {
            val joinRoom = Intent(this, JoinRoomActivity::class.java)
            startActivity(joinRoom)
        }

        cvLeaderBoard.setOnClickListener {
            val rank = Intent(this, LeaderBoardActivity::class.java)
            startActivity(rank)
        }

        cvLogout.setOnClickListener {
            alertDialogBuilder.showWarningWithOptions(R.string.menu_title, R.string.confirm_logout,
            positiveAction = {
                UserRepository(this).logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            })
        }
    }

    private fun playMusic() {
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
