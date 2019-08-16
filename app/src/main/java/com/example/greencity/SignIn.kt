package com.example.greencity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mongodb.client.model.Filters.eq
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import org.bson.Document


class SignIn : AppCompatActivity() {
    private var nameSignIn: EditText? = null
    private var surnameSignIn: EditText? = null
    private var emailSignIn: EditText? = null
    private var passwordSignIn: EditText? = null

    private var isValid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        //Setup connectionMongoDB with Util Class
        val client = ConnectionDBUtil.defaultAppClient()
        val mongoClient = ConnectionDBUtil.getServiceClient()
        val collection = ConnectionDBUtil.getDB();
        //end setup

        this.nameSignIn = findViewById<EditText>(R.id.signin_nome)
        this.surnameSignIn = findViewById<EditText>(R.id.signin_cognome)
        this.emailSignIn = findViewById<EditText>(R.id.signin_email)
        this.passwordSignIn = findViewById<EditText>(R.id.signin_password)

        val users = mutableListOf<Document>()
        var cursor = collection.find(eq("nome", "luca"))


        Toast.makeText(this, cursor.toString(), Toast.LENGTH_LONG).show()

        val register: Button = findViewById(R.id.signin_btn)

        register.setOnClickListener {
            var isValid = true;
            //check if exists user on click registrati
            collection
                .find()
                .into(users)
                .addOnSuccessListener {
                    users.map {
                        users.forEach {
                            // Extract only the 'email' field
                            var emailDB = it.getString("email")
                            Log.i("LUCA2", it.getString("email").toString())
                            checkExistEmail(emailDB)
                        }
                        // of each document
                    }
                    // More code here
                }
            checkName()
            checkSurname()
            checkEmail()
            checkPassword()
            if (isValid) {
                sendUser(client, collection)
            }
        }
    }

    fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkName() {
        if (nameSignIn?.text.toString().trim().isEmpty()) {
            nameSignIn?.setError("Nome Obbligatorio");
            isValid = false
        } else {
            nameSignIn?.setError(null)
        }
    }

    private fun checkSurname() {
        if (surnameSignIn?.text.toString().trim().isEmpty()) {
            surnameSignIn?.setError("Cognome Obbligatorio")
            isValid = false
        } else {
            surnameSignIn?.setError(null)
        }
    }

    private fun checkEmail() {
        if (emailSignIn?.text.toString().trim().length == 0) {
            emailSignIn?.setError("Email Obbligatorio")
            isValid = false
        } else {
            if (isEmailValid(emailSignIn?.text.toString())) {
                Toast.makeText(this, "Valid Email", Toast.LENGTH_LONG).show()
                emailSignIn?.setError(null)
            } else {
                isValid = false
                emailSignIn?.setError("Inserire una mail valida")
            }
        }
    }

    private fun checkExistEmail(it: String) {
        if (emailSignIn?.text.toString().trim().equals(it)) {
            isValid = false;
            Log.i("EMAIL ESISTENTE", emailSignIn?.text.toString().trim())
            Toast.makeText(this, "Utente gia registrato con questa email", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPassword() {
        if (passwordSignIn?.text.toString().trim().length == 0) {
            passwordSignIn?.setError("Password Obbligatorio");
            isValid = false
        } else {
            if (!isValidPassword(passwordSignIn?.text.toString())) {
                isValid = false
                passwordSignIn?.setError("La password deve contenere 8 caratteri tra cui maiuscolo, minuscolo,numeri e caratteri speciali")
            }
        }
    }

    private fun sendUser(client: StitchAppClient, collection: RemoteMongoCollection<Document>) {
        val document = Document()
        document["nome"] = nameSignIn?.text.toString()
        document["cognome"] = surnameSignIn?.text.toString()
        document["email"] = emailSignIn?.text.toString()
        document["password"] = passwordSignIn?.text.toString()
        document["user_id"] = client.auth.user!!.id
        collection.insertOne(document).addOnSuccessListener {
            Log.d("STITCH", "inserito")
        }
    }
}
