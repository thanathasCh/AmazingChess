package com.example.androidmidterm.ChessPieces

class Pawn(color: String) : Pieces() {

    init {
        super.TYPE = "PAWN"
        super.COLOR = color
    }

    override fun possibleMove(x: Int, y: Int): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}