package com.norm.countries.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.norm.countries.R
import com.norm.countries.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            kotlin.run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, SPLASH_TIME)
    }

    companion object {
        private const val SPLASH_TIME = 1000L
    }
}
