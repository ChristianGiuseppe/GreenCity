package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.greencity.R

class SplashGreenCity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_green_city)
        Handler().postDelayed({
                val iLogin = Intent(this, Navbar::class.java)
                startActivity(iLogin)
                finish()
        }, 3000)

    }
}
