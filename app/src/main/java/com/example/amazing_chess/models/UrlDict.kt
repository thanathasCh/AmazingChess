package com.example.amazing_chess.models

class UrlDict {
    private val baseUrl = "https://8130cb92f8e2.ngrok.io/"
    private val userUrl = baseUrl + "User/"
    private val gameUrl = baseUrl + "Game/"
    private val leaderboardUrl = baseUrl + "LeaderBoard"

    val login = userUrl + "Login"
    val createAccount = userUrl + "CreateAccount"
    val checkDuplicate = userUrl + "CheckDuplicate"

    val leaderBoard = leaderboardUrl + "FetchLeaderBoard"
}