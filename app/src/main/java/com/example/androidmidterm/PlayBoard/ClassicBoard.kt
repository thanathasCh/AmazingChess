package com.example.androidmidterm.PlayBoard

import android.content.Intent
import android.os.Bundle
import com.example.androidmidterm.ChessPieces.Pieces
import com.example.androidmidterm.R

class ClassicBoard : Board() {
    lateinit var Board: Array<Array<out Pieces?>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_board)
    }
}