package com.example.androidmidterm.Menu.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.UserViewModel
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val a = ArrayList<UserViewModel>()

        rvRanking.layoutManager = LinearLayoutManager(this)
        rvRanking.adapter = RankingAdapter(this, a)
    }
}
