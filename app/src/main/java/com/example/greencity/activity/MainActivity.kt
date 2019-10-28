package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.greencity.R


class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null
    private var btnLogin: Button? = null
    private var emailAccedi: EditText? = null
    private var passwordAccedi: EditText? = null
    private var layoutProgress: ConstraintLayout? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.emailAccedi = findViewById(R.id.email_user)
        this.passwordAccedi = findViewById(R.id.signin_password)
        this.textSignIn = findViewById(R.id.sign_in)
        this.btnLogin = findViewById(R.id.login_btn)
        this.layoutProgress = findViewById(R.id.wrapper_progress_circular_signin)
        this.progressBar = findViewById(R.id.progress_circular_signin)

        textSignIn?.setOnClickListener {
            //CHIAMARE LA LISTA DELLE REGIONI
            val iSignin = Intent(this, SignIn::class.java)
            startActivity(iSignin)
        }

        btnLogin?.setOnClickListener {
            //VERIFICA CHE IL LOGIN E' STATO EFFETTUATO CORRETTAMENTE
            val iLogin = Intent(this, SplashGreenCity::class.java)
            startActivity(iLogin)
        }
    }
}
