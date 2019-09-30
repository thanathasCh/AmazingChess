package com.example.androidmidterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.Providers.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val u = User()
//        u.LogIn("test007", "0")
        u.getValue()
    }
}
