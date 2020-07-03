package com.example.amazing_chess.models

class UrlDict {
    private val base_url = "/"
    private val user_url = base_url + "User/"
    private val game_url = base_url + "Game/"
    private val leaderboard_url = base_url + "LeaderBoard"

    val login = user_url + "Login"
    val createAccount = user_url + "CreateAccount"
    val checkDuplicate = user_url + "CheckDuplicate"
}