package com.example.androidmidterm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.Services.Pieces
import com.example.androidmidterm.Services.createBoardBlack
import com.example.androidmidterm.Services.createBoardWhite

class VmsBoard: AppCompatActivity() {

    lateinit var Board: Array<Array<out Pieces?>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vms_board)

        Board = when (intent.getIntExtra("STATUS", 0)) {
            0 -> createBoardBlack()
            else -> createBoardWhite()
        }




    }
}