package com.example.androidmidterm.PlayBoard

import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.Services.createBoardBlack
import com.example.androidmidterm.Services.createBoardWhite
import com.example.androidmidterm.Services.global_playing_status

open class Board: AppCompatActivity() {

    var MainBoard: Array<Array<out Pieces?>> = when (global_playing_status) {
        0 -> createBoardBlack()
        else -> createBoardWhite()
    }

    fun move (or: Pair<Int, Int>, des: Pair<Int, Int>) {

    }
}