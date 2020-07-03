package com.example.amazing_chess.models.chess_pieces

import com.example.amazing_chess.services.MY_COLOR
import com.example.amazing_chess.services.isExceedBoard

class King(color: String) : Pieces() {
    init {
        super.TYPE = "KING"
        super.COLOR = color
    }

    override fun possibleMove(
        pos: Pair<Int, Int>,
        board: Array<Array<Pieces>>
    ): List<Pair<Int, Int>> {
        val x = pos.first
        val y = pos.second

        val can = arrayListOf<Pair<Int, Int>>()
        val moves = arrayOf(Pair(x - 1, y), Pair(x, y + 1), Pair(x, y - 1), Pair(x + 1, y),
            Pair(x - 1, y - 1), Pair(x - 1, y + 1), Pair(x + 1, y - 1), Pair(x + 1, y + 1))

        for (i in moves) {
            if (!i.isExceedBoard() && board[i.first][i.second].COLOR != MY_COLOR()) {
                can.add(i)
            }
        }

        return can
    }
}