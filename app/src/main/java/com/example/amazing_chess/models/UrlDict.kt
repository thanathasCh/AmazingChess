package com.example.amazing_chess.models

class UrlDict {
    private val base_url = "https://8130cb92f8e2.ngrok.io/"
    private val user_url = base_url + "User/"
    private val game_url = base_url + "Game/"
    private val leaderboard_url = base_url + "LeaderBoard"

    val login = user_url + "Login"
    val createAccount = user_url + "CreateAccount"
    val checkDuplicate = user_url + "CheckDuplicate"
}