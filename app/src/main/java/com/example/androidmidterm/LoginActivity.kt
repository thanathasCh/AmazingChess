package com.example.androidmidterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidmidterm.Services.DbContext
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginAuthentication(userName: String, password: String) {
        val data = db.Users.orderByChild("Name").equalTo(userName)
        data.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.children.iterator().next().child("Password").value.toString() == password) {
                    //TODO if userName and password are corrent.
                } else {
                    //TODO userName or password is not correct.
                }
            }

        })
    }
}
