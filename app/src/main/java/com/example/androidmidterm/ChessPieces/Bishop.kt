package com.example.androidmidterm.ChessPieces

class Bishop(color: String) : Pieces() {
    init {
        super.TYPE = "BISHOP"
        super.COLOR = color
    }

    override fun possibleMove(x: Int, y: Int): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}