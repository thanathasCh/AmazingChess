package com.example.amazing_chess.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.amazing_chess.R
import com.example.amazing_chess.views.splashScreen.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text.setOnClickListener {
            val intent = Intent(this, SplashScreenActivity::class.java)
            startActivity(intent)
        }
    }
}
