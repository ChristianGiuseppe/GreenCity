package com.example.greencity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSignIn = findViewById(R.id.sign_in)
        textSignIn?.setOnClickListener(View.OnClickListener {
            val iSignIn = Intent(this, SignIn::class.java)
            startActivity(iSignIn)

            val conDB: ConnectionDB? = null
            conDB?.conncetionDB()
        })

    }
}
