package com.example.androidmidterm.PlayBoard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.*

class BlueBoard: AppCompatActivity() {

    lateinit var Board: Array<Array<out Pieces?>>
    val db = DbContext()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_board)

        Board = when (intent.getIntExtra("STATUS", 0)) {
            0 -> createBoardBlack()
            else -> createBoardWhite()
        }
    }
}