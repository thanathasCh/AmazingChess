package com.example.amazing_chess.data.remotes

import com.example.amazing_chess.models.UrlDict
import com.example.amazing_chess.models.dataModels.LeaderBoard
import com.example.amazing_chess.services.fromJson
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson

class LeaderBoardApi {
    private val urlDict = UrlDict()
    private val mGson = Gson()

    fun fetchLeaderBoard(callback: (ArrayList<LeaderBoard>) -> Unit) {
        Fuel.get(urlDict.leaderBoard)
            .responseString { request, response, result ->
                if (response.statusCode == 200) {
                    callback(mGson.fromJson<ArrayList<LeaderBoard>>(result.component1() ?: "[]"))
                } else {
                    callback(ArrayList())
                }
            }
    }
}