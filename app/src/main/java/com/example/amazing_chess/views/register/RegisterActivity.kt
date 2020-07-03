package com.example.amazing_chess.views.register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.data.repositories.UserRepository
import com.example.amazing_chess.models.dataModels.User
import com.example.amazing_chess.services.*
import com.example.amazing_chess.views.menu.MenuActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val userRepository = UserRepository(this)
    private val alertDialogBuilder = AlertDialogBuilder(this)
    private val regex = RegexValidator()
    private lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        loadingBar = ProgressDialogBuilder(this).loadingBar()

        cvRegister.setOnClickListener {
            val firstName = etName.text.toString()
            val lastName = etLastName.text.toString()
            val userName = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString().encrypt()
            val confirmedPassword = etConfirmPassword.text.toString().encrypt()

            if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty()
                || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()
            ) {
                alertDialogBuilder.showWarning(R.string.register_title, R.string.empty_error)
            } else if (password != confirmedPassword) {
                alertDialogBuilder.showWarning(
                    R.string.register_title,
                    R.string.password_confirmation_error
                )
            } else if (!regex.validateEmail(email)) {
                alertDialogBuilder.showWarning(R.string.register_title, R.string.invalid_email)
            } else {
                loadingBar.show()

                Thread(Runnable {
                    userRepository.checkDuplicate(userName, email) { isDuplicate ->
                        if (isDuplicate) {
                            loadingBar.dismiss()
                            alertDialogBuilder.showWarning(
                                R.string.register_title,
                                R.string.duplicate_account
                            )
                        } else {
                            val user = User(
                                firstName = firstName,
                                lastName = lastName,
                                userName = userName,
                                email = email,
                                password = password
                            )
                            userRepository.createAccount(user) { isSuccess ->
                                loadingBar.dismiss()

                                if (isSuccess) {
                                    alertDialogBuilder.showInfo(
                                        R.string.register_title,
                                        R.string.create_account_successful
                                    ) {
                                        finish()
                                    }
                                } else {
                                    alertDialogBuilder.showWarning(
                                        R.string.register_title,
                                        R.string.error_occurred
                                    )
                                }
                            }
                        }
                    }
                }).start()
            }
        }
    }
}

