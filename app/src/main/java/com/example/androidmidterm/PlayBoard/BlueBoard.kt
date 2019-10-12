package com.example.androidmidterm.PlayBoard

import android.os.Bundle
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.*

class BlueBoard: Board() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_board)
    }
}