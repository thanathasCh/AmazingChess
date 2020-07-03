package com.example.amazing_chess.models.chessPieces

import com.example.amazing_chess.services.MY_COLOR
import com.example.amazing_chess.services.OP_COLOR

class Queen(color: String) : Pieces() {
    init {
        super.TYPE = "QUEEN"
        super.COLOR = color
    }

    override fun possibleMove(
        pos: Pair<Int, Int>,
        board: Array<Array<Pieces>>
    ): List<Pair<Int, Int>> {
        val x = pos.first
        val y = pos.second

        val moves = arrayListOf<Pair<Int, Int>>()

        val directions = arrayOf(Pair(-1, -1), Pair(-1, 1), Pair(1, -1), Pair(1, 1), Pair(0, -1), Pair(-1, 0), Pair(0, 1), Pair(1, 0))

        for (i in directions) {
            var bufX = x + i.first
            var bufY = y + i.second
            var fp = false

            while (bufX in 0..7 && bufY in 0..7) {
                if (board[bufX][bufY].COLOR == MY_COLOR() || fp) {
                    break
                }

                if (board[bufX][bufY].COLOR == OP_COLOR()) {
                    fp = !fp
                }
                moves.add(Pair(bufX, bufY))
                bufX += i.first
                bufY += i.second
            }
        }

        return moves
    }
}