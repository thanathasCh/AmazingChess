package com.example.amazing_chess.views.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.amazing_chess.R
import com.example.amazing_chess.data.repositories.UserRepository
import com.example.amazing_chess.services.AlertDialogBuilder
import com.example.amazing_chess.services.ProgressDialogBuilder
import com.example.amazing_chess.services.encrypt
import com.example.amazing_chess.views.menu.MenuActivity
import com.example.amazing_chess.views.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val alertBuilder = AlertDialogBuilder(this)
    private val userRepository = UserRepository(this)
    private lateinit var loadingBar: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadingBar = ProgressDialogBuilder(this).loadingBar(R.string.loading)

        cvLogin.setOnClickListener {
            val userName = tvUserName.text.toString()
            val password = tvPassword.text.toString().encrypt()

            if (userName.isEmpty() || password.isEmpty()) {
                alertBuilder.showWarning(R.string.login_title, R.string.empty_error)
            } else {
                loadingBar.show()

                Thread(Runnable {
                    userRepository.login(userName, password) {
                        loadingBar.dismiss()

                        if (it) {
                            alertBuilder.showWarning(
                                R.string.login_title,
                                R.string.login_successful
                            ) {
                                startActivity(Intent(this, MenuActivity::class.java))
                            }
                        } else {
                            alertBuilder.showWarning(
                                R.string.login_title,
                                R.string.wrong_username_password
                            )
                        }
                    }
                }).start()
            }
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
