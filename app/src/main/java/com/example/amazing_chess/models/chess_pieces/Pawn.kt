package com.example.amazing_chess.models.chess_pieces

import com.example.amazing_chess.services.MY_COLOR
import com.example.amazing_chess.services.OP_COLOR
import com.example.amazing_chess.services.isExceedBoard

class Pawn(color: String) : Pieces() {

    init {
        super.TYPE = "PAWN"
        super.COLOR = color
    }

    override fun possibleMove(
        pos: Pair<Int, Int>,
        board: Array<Array<Pieces>>
    ): List<Pair<Int, Int>> {
        val moves = arrayListOf<Pair<Int, Int>>()
        val first = Pair(pos.first - 1, pos.second - 1)
        val second = Pair(pos.first - 1, pos.second + 1)
        val third = Pair(pos.first - 1, pos.second)

        if (!first.isExceedBoard() && board[first.first][first.second].COLOR == OP_COLOR()) {
            moves.add(first)
        }

        if (!second.isExceedBoard() && board[second.first][second.second].COLOR == OP_COLOR()) {
            moves.add(second)
        }

        if (!third.isExceedBoard() && board[third.first][third.second].COLOR != MY_COLOR()) {
            moves.add(third)
        }

        if (pos.first == 6) {
            moves.add(Pair(pos.first - 2, pos.second))
        }

        return moves
    }
}