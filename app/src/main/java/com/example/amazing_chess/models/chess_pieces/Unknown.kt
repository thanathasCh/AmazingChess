package com.example.amazing_chess.models.chess_pieces

class Unknown : Pieces() {
    init {
        super.TYPE = ""
        super.COLOR = ""
    }

    override fun possibleMove(
        pos: Pair<Int, Int>,
        board: Array<Array<Pieces>>
    ): List<Pair<Int, Int>> {
        TODO("not implemented")
    }
}