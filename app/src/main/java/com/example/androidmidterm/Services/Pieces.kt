package com.example.androidmidterm.Services

class Pieces {
    private var N_TYPE: Int
    private var N_COLOR: Int

    var TYPE: String
        get() = when (this.N_TYPE) {
            1 -> "PAWN"
            2 -> "ROOK"
            4 -> "KNIGHT"
            5 -> "BISHOP"
            6 -> "QUEEN"
            7 -> "KING"
            else -> "UNKNOWN"
        }
        set(value) {
            this.N_TYPE = when (value) {
                "PAWN" -> 1
                "ROOK" -> 2
                "KNIGHT" -> 3
                "BISHOP" -> 4
                "QUEEN" -> 5
                "KING" -> 6
                else -> 0
            }
        }

    var COLOR: String
        get() = when (this.N_COLOR) {
            1 -> "WHITE"
            2 -> "BLACK"
            else -> "UNKNOWN"
        }
        set(value) {
            this.N_TYPE = when (value) {
                "WHITE" -> 1
                "BLACK" -> 2
                else -> 0
            }
        }

    constructor() {
        this.N_TYPE = 0
        this.N_COLOR = 0
    }

    constructor(type: Int, color: Int) {
        this.N_TYPE = type
        this.N_COLOR = color
    }

    fun MoveTo(x: Int, y: Int){

    }

}