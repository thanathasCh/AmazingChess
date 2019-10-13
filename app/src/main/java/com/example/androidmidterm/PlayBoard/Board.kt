package com.example.androidmidterm.PlayBoard

import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.ChessPieces.Unknown
import com.example.androidmidterm.Services.OP_COLOR
import com.example.androidmidterm.Services.createBoardBlack
import com.example.androidmidterm.Services.createBoardWhite
import com.example.androidmidterm.Services.global_player_status

open class Board : AppCompatActivity() {

    var MainBoard: Array<Array<Pieces>> = when (global_player_status) {
        0 -> createBoardBlack()
        else -> createBoardWhite()
    }

    fun move(pos: Pair<Int, Int>, des: Pair<Int, Int>) {
        if (MainBoard[des.first][des.second].COLOR != OP_COLOR) {
            MainBoard[pos.first][pos.second] = MainBoard[des.first][des.second].also {
                MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            }
        } else {
            MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            MainBoard[pos.first][pos.second] = Unknown()
        }
    }
}