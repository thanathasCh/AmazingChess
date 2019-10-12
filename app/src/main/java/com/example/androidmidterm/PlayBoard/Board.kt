package com.example.androidmidterm.PlayBoard

import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.Services.createBoardBlack
import com.example.androidmidterm.Services.createBoardWhite

open class Board: AppCompatActivity() {
//    var MainBoard: Array<Array<out Pieces?>> = when (intent.getIntExtra("STATUS", 0)) {
//        0 -> createBoardBlack()
//        else -> createBoardWhite()
//    }

    var MainBoard = createBoardBlack()
    fun move (or: Pair<Int, Int>, des: Pair<Int, Int>) {

    }
}