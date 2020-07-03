package com.example.amazing_chess.views.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.amazing_chess.R
import com.example.amazing_chess.data.locals.UserSharedPreference
import com.example.amazing_chess.views.login.LoginActivity
import com.example.amazing_chess.views.menu.MenuActivity

class SplashScreenActivity : AppCompatActivity() {

    val time: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            run {
                if (UserSharedPreference(this).isLogin()) {
                    startActivity(Intent(this, MenuActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                finish()
            }
        }, time)
    }
}
