package com.example.amazing_chess.views.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.services.DbContext
import com.example.amazing_chess.services.USER_ID
import com.example.amazing_chess.services.encrypt
import com.example.amazing_chess.services.warningBox
import com.example.amazing_chess.views.menu.MenuActivity
import com.example.amazing_chess.views.register.RegisterActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvLogin.setOnClickListener {
            loginAuthentication(tvUserName.text.toString(), tvPassword.text.toString().encrypt())
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginAuthentication(userName: String, password: String) {
        val data = db.Users.orderByChild("Name").equalTo(userName)
        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) { }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.children.iterator().next().child("Password").value.toString() == password) {
                    USER_ID = dataSnapshot.children.iterator().next().key!!
                    startActivity(Intent(applicationContext, MenuActivity::class.java))
                } else {
                    warningBox(this@LoginActivity, "Account not found!",
                        "Invalid Username or Password")
                }
            }

        })
    }
}
