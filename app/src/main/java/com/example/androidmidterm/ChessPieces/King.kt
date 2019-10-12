package com.example.androidmidterm.ChessPieces

class King(color: String) : Pieces() {
    init {
        super.TYPE = "KING"
        super.COLOR = color
    }

    override fun possibleMove(x: Int, y: Int): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}