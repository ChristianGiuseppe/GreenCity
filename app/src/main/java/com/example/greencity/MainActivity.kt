package com.example.greencity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import org.bson.Document


class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null
    private var btnLogin:Button? = null
    private var emailAccedi: EditText? = null
    private var passwordAccedi: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client = Stitch.initializeDefaultAppClient("stitchapp-oeziy")
        val client2 = ConnectionDBUtil.defaultAppClient()
        val mongoClient = ConnectionDBUtil.getServiceClient()
        val collection = ConnectionDBUtil.getDB()
        this.emailAccedi = findViewById<EditText>(R.id.email_user)
        this.passwordAccedi = findViewById<EditText>(R.id.signin_password)
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
        this.btnLogin = this.findViewById(R.id.login_btn)
        textSignIn?.setOnClickListener {
            val iSignIn = Intent(this, SignIn::class.java)
            startActivity(iSignIn)
        }

        btnLogin?.setOnClickListener {
            val iLogin = Intent(this, Navbar::class.java)
            Toast.makeText(this,emailAccedi?.text.toString(),Toast.LENGTH_LONG).show()
            val users = mutableListOf<Document>()
            //check if exists user on click registrati
            collection
                .find()
                .into(users)
                .addOnSuccessListener {
                    users.map {
                        users.forEach {
                            Log.i("Utente non regitsrato",emailAccedi?.text.toString())
                            // Extract only the 'email' field
                            var emailDB = it.getString("email")
                            Log.i("LUCA2", it.getString("email").toString())
                            if(emailAccedi?.text.toString().trim().equals(emailDB)){
                                    startActivity(iLogin)
                                }else{
                                    Log.i("Utente non regitsrato",emailAccedi?.text.toString())
                                }
                            }
                        }
                        // of each document
                    }
                    // More code here
                }
        }

}
