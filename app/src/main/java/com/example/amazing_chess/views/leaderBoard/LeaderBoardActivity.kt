package com.example.amazing_chess.views.leaderBoard

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazing_chess.R
import com.example.amazing_chess.data.repositories.LeaderBoardRepository
import com.example.amazing_chess.models.dataModels.LeaderBoard
import com.example.amazing_chess.services.*
import kotlinx.android.synthetic.main.activity_leaderboard.*

class LeaderBoardActivity : AppCompatActivity() {
    private val leaderBoardRepository = LeaderBoardRepository(this)
    private lateinit var loadingBar: ProgressDialog

    private val users = arrayListOf<LeaderBoard>()
    private val leaderBoardAdapter = LeaderBoardAdapter(this, users)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)
        loadingBar = ProgressDialogBuilder(this).loadingBar()

        loadingBar.show()
        rvLeaderBoard.layoutManager = LinearLayoutManager(this)
        rvLeaderBoard.adapter = leaderBoardAdapter
        updateLeaderBoard()

        srl_leader_board.setOnRefreshListener {
            updateLeaderBoard()
        }
    }

    private fun updateLeaderBoard() {
        Thread(Runnable {
            leaderBoardRepository.fetchLeaderBoard {
                if (it.isNotEmpty()) {
                    users.clear()
                    users.addAll(it)
                    leaderBoardAdapter.notifyDataSetChanged()
                }
                loadingBar.dismiss()
            }
        }).start()
    }
}
