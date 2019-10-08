package com.example.androidmidterm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.Services.*
import com.example.androidmidterm.SplashScreen.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_main.*

class chess : AppCompatActivity() {

    val db = DbContext()
    lateinit var Board: Array<Array<out Pieces?>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(global_board)

        Board = when (intent.getIntExtra("STATUS", 0)) {
            0 -> createBoardBlack()
            else -> createBoardWhite()
        }


    }
}