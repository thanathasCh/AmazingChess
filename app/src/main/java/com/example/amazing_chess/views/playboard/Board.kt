package com.example.amazing_chess.views.playboard

import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.models.chess_pieces.Pieces
import com.example.amazing_chess.models.chess_pieces.Unknown
import com.example.amazing_chess.services.*

open class Board : AppCompatActivity() {

    var MainBoard: Array<Array<Pieces>> = when (global_player_status) {
        0 -> createBoardBlack()
        else -> createBoardWhite()
    }

    fun move(pos: Pair<Int, Int>, des: Pair<Int, Int>) {
        if (MainBoard[des.first][des.second].COLOR != OP_COLOR()) {
            MainBoard[pos.first][pos.second] = MainBoard[des.first][des.second].also {
                MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            }
        } else {
            MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            MainBoard[pos.first][pos.second] = Unknown()
        }
    }

    fun op_move(pos: Pair<Int, Int>, des:Pair<Int, Int>) {
        if (MainBoard[des.first][des.second].COLOR != MY_COLOR()) {
            MainBoard[pos.first][des.second] = MainBoard[des.first][des.second].also {
                MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            }
        } else {
            MainBoard[des.first][des.second] = MainBoard[pos.first][pos.second]
            MainBoard[pos.first][pos.second] = Unknown()
        }
    }
}