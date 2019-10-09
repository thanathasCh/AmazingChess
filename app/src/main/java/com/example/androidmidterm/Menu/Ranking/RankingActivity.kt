package com.example.androidmidterm.Menu.Ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.UserModel
import com.example.androidmidterm.Services.UserViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val data = db.Users.orderByChild("Score").limitToLast(10)

        data.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val top10 = arrayListOf<UserViewModel>()

                dataSnapshot.children.forEach {
                    top10.add(UserModel(
                        Id = it.key.toString(),
                        Name = it.child("Name").value.toString(),
                        FirstName = it.child("FirstName").value.toString(),
                        LastName =  it.child("LastName").value.toString(),
                        Score = it.child("Score").value.toString().toInt()
                    ).toViewModel())
                }
                top10.sortByDescending { it.Score }
                Log.d("Data", top10.toString())
                rvRanking.layoutManager = LinearLayoutManager(this@RankingActivity)
                rvRanking.adapter = RankingAdapter(this@RankingActivity, top10)
            }

        })
    }
}
