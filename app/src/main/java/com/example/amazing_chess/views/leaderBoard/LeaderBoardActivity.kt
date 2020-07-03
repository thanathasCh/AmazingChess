package com.example.amazing_chess.views.leaderBoard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.amazing_chess.R
import com.example.amazing_chess.services.DbContext
import com.example.amazing_chess.services.UserModel
import com.example.amazing_chess.services.UserViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_ranking.*

class LeaderBoardActivity : AppCompatActivity() {

    private val db = DbContext()
    private val top10 = arrayListOf<UserViewModel>()
    private val adapter = LeaderBoardAdapter(this, top10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        rvRanking.layoutManager = LinearLayoutManager(this@LeaderBoardActivity)
        rvRanking.adapter = adapter
        val data = db.Users.orderByChild("Score").limitToLast(10)

        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    top10.add(
                        UserModel(
                            Id = it.key.toString(),
                            Name = it.child("Name").value.toString(),
                            FirstName = it.child("FirstName").value.toString(),
                            LastName = it.child("LastName").value.toString(),
                            Score = it.child("Score").value.toString().toInt()
                        ).toViewModel()
                    )
                }
                top10.sortByDescending { it.Score }
                adapter.notifyDataSetChanged()
            }

        })
    }
}
