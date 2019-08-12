package com.example.greencity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential


class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = Stitch.initializeDefaultAppClient("greencity-pfvds")
        client.auth.loginWithCredential(AnonymousCredential()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(
                    "myApp", String.format(
                        "logged in as user %s with provider %s",
                        task.result.id,
                        task.result.loggedInProviderType
                    )
                )
            } else {
                Log.e("myApp", "failed to log in", task.exception)
            }
        }
        setContentView(R.layout.activity_main)
        textSignIn = findViewById(R.id.sign_in)
        textSignIn?.setOnClickListener {
            val iSignIn = Intent(this, SignIn::class.java)
            startActivity(iSignIn)
        }

    }
}
