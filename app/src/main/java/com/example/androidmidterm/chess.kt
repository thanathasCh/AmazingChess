package com.example.androidmidterm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidmidterm.SplashScreen.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_main.*

class chess : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_board)

    }
}