package com.example.amazing_chess.views.leaderBoard

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.amazing_chess.R
import com.example.amazing_chess.services.UserViewModel
import com.example.amazing_chess.services.inflate
import kotlinx.android.synthetic.main.ranking_item.view.*

class LeaderBoardAdapter(private val activity: Activity, private val    users: java.util.ArrayList<UserViewModel>):
        RecyclerView.Adapter<LeaderBoardAdapter.RankingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingHolder {
        val inflatedView = parent.inflate(R.layout.ranking_item, false)

        return RankingHolder(inflatedView)
    }
    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RankingHolder, position: Int) {
        holder.bindUser(users[position], position + 1)
    }

    class RankingHolder(v: View):
            RecyclerView.ViewHolder(v) {
        private val view: View = v

        fun bindUser(user: UserViewModel, position: Int) {
            with (view) {
                tvName.text = user.FullName
                tvScore.text = user.Score.toString()
                tvRank.text = position.toString()
            }
        }
    }
}