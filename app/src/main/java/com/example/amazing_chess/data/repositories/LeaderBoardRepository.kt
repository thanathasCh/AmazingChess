package com.example.amazing_chess.data.repositories

import android.content.Context
import com.example.amazing_chess.data.remotes.LeaderBoardApi
import com.example.amazing_chess.models.dataModels.LeaderBoard

class LeaderBoardRepository(private val context: Context) {
    private val leaderBoardApi = LeaderBoardApi()

    fun fetchLeaderBoard(callback: (ArrayList<LeaderBoard>) -> Unit) = leaderBoardApi.fetchLeaderBoard(callback)
}