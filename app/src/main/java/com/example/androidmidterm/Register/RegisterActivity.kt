package com.example.androidmidterm.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.androidmidterm.Menu.MenuActivity
import com.example.androidmidterm.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvRegisterButton.setOnClickListener {
            if (etPassword == etConfirmPassword) {
                var intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)

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

