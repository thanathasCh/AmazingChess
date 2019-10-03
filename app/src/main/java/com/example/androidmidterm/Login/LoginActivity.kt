package com.example.androidmidterm.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.Menu.MenuActivity
import com.example.androidmidterm.R
import com.example.androidmidterm.Register.RegisterActivity
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.encrypt
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
//            var intent = Intent(applicationContext, MenuActivity::class.java)
//            startActivity(intent)
            loginAuthentication(tvUserName.text.toString(), encrypt(tvPassword.text.toString()))
        }

        tvRegister.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun loginAuthentication(userName: String, password: String) {
        val data = db.Users.orderByChild("Name").equalTo(userName)
        data.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists() && snapshot.children.iterator().next().child("Password").value.toString() == password) {
                    tvLogin.setOnClickListener {
                        var intent = Intent(applicationContext, MenuActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    tvLogin.setOnClickListener {
                        val mAlertDialog = AlertDialog.Builder(this@LoginActivity)
                        mAlertDialog.setTitle("Account not found!")
                        mAlertDialog.setMessage("Invalid Username or Password")
                        mAlertDialog.setIcon(R.drawable.p_momo)

                        mAlertDialog.setNegativeButton("OK") {dialog, id ->
                            dialog.dismiss()
                        }
                        mAlertDialog.show()
                    }
                }
            }

        })
    }
}
