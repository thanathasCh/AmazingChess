package com.example.androidmidterm.ChessPieces

import com.example.androidmidterm.Services.MY_COLOR
import com.example.androidmidterm.Services.isExceedBoard

class Pawn(color: String) : Pieces() {

    init {
        super.TYPE = "PAWN"
        super.COLOR = color
    } //TODO walk and swap

    override fun possibleMove(
        pos: Pair<Int, Int>,
        board: Array<Array<Pieces>>
    ): List<Pair<Int, Int>> {
        val moves = arrayListOf<Pair<Int, Int>>()
        val first = Pair(pos.first - 1, pos.second - 1)
        val second = Pair(pos.first - 1, pos.second + 1)

        if (!first.isExceedBoard() && board[first.first][first.second].COLOR != MY_COLOR()) {
            moves.add(first)
        }

        if (!second.isExceedBoard() && board[second.first][second.second].COLOR != MY_COLOR()) {
            moves.add(second)
        }

        return moves
    }
}