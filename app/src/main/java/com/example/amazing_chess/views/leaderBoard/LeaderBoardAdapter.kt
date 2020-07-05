package com.example.amazing_chess.views.leaderBoard

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing_chess.R
import com.example.amazing_chess.models.dataModels.LeaderBoard
import com.example.amazing_chess.services.inflate
import kotlinx.android.synthetic.main.leaderboard_item.view.*

class LeaderBoardAdapter(private val activity: Activity, private val users: java.util.ArrayList<LeaderBoard>):
        RecyclerView.Adapter<LeaderBoardAdapter.RankingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingHolder {
        val inflatedView = parent.inflate(R.layout.leaderboard_item, false)
        return RankingHolder(inflatedView)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RankingHolder, position: Int) {
        holder.bindUser(users[position], position + 1)
    }

    class RankingHolder(v: View): RecyclerView.ViewHolder(v) {
        private val view: View = v

        fun bindUser(user: LeaderBoard, position: Int) {
            with (view) {
                tvName.text = user.fullName
                tvUserName.text = user.userName
                tvScore.text = user.score.toString()
                tvRank.text = position.toString()

                if (user.isUser) {
                    setBackgroundColor(ContextCompat.getColor(context, R.color.button_background))
                }
            }
        }
    }
}