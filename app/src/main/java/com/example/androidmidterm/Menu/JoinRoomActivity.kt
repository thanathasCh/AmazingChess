package com.example.androidmidterm.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.MainActivity
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_join_room.*

class JoinRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_room)

        tvJoin.setOnClickListener {
            var intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
    }
}
