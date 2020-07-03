package com.example.amazing_chess.views.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.services.*
import com.example.amazing_chess.views.menu.MenuActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val db = DbContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tvRegisterButton.setOnClickListener {
            if (etPassword.text.toString().encrypt() != etConfirmPassword.text.toString().encrypt() || validateUsername()) {
                registerToSystem()
            } else {
                warningBox(this@RegisterActivity, "Invalid register!",
                    "Username or Password is incorrect, Try again :)")
            }
        }
    }

    private fun validateUsername(): Boolean {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        return if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            etUsername.error = "Field can't be empty"
            etPassword.error = "Field can't be empty"
            etConfirmPassword.error = "Field can't be empty"
            false
        } else {
            etUsername.error = null
            true
        }
    }

    private fun registerToSystem() {
        val data = db.Users.orderByChild("Name").equalTo(etUsername.text.toString())

        data.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) { }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    warningBox(this@RegisterActivity, "Invalid Register!",
                        "User is already existed, Please choose other user name.")
                } else {
                    val key = db.Users.push().key

                    val User = UserModel (
                        Id = key!!,
                        Name = etUsername.text.toString(),
                        FirstName = etName.text.toString(),
                        LastName = etLastName.text.toString(),
                        Password = etPassword.text.toString().encrypt(),
                        Score = 0
                    ).toMap()

                    val childUpdate = HashMap<String, Any>()
                    childUpdate["/$key"] = User

                    db.Users.updateChildren(childUpdate)

                    USER_ID = key
                    Toast.makeText(this@RegisterActivity, "Register Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, MenuActivity::class.java))
                }
            }

        })
    }
}

