package com.example.androidmidterm.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.Menu.MenuActivity
import com.example.androidmidterm.R
import com.example.androidmidterm.Services.DbContext
import com.example.androidmidterm.Services.GameRoomModel
import com.example.androidmidterm.Services.UserModel
import com.example.androidmidterm.Services.encrypt
import kotlinx.android.synthetic.main.activity_register.*
import java.sql.Date

class RegisterActivity : AppCompatActivity() {

    val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvRegisterButton.setOnClickListener {
            if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                val key = db.Users.push().key

                val User = UserModel (
                    Id = key!!,
                    Name = etUsername.text.toString(),
                    FirstName = etName.text.toString(),
                    LastName = etLastName.text.toString(),
                    Password = etPassword.text.toString().encrypt(),
                    Score = 0,
                    CreatedAt = Date(System.currentTimeMillis()).toString(),
                    EditedAt = Date(System.currentTimeMillis()).toString()
                ).toMap()

                val childUpdate = HashMap<String, Any>()
                childUpdate["/$key"] = User

                db.Users.updateChildren(childUpdate)
                //TODO James - after done registration
            } else {
                val mAlertDialog = AlertDialog.Builder(this@RegisterActivity)
                mAlertDialog.setTitle("Invalid Register!")
                mAlertDialog.setMessage("Username or Password is wrong, Try again :)")
                mAlertDialog.setIcon(R.drawable.p_momo)

                mAlertDialog.setNegativeButton("OK") { dialog, id ->
                    dialog.dismiss()
                }
                mAlertDialog.show()
                validateUsername()
            }
        }
    }

    private fun validateUsername(): Boolean {
        var username = etUsername.text.toString().trim()
        var password = etPassword.text.toString().trim()
        var confirmPassword = etConfirmPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            etUsername.error = "Field can't be empty"
            etPassword.error = "Field can't be empty"
            etConfirmPassword.error = "Field can't be empty"
            return false
        } else {
            etUsername.error = null
            return true
        }
    }
}

