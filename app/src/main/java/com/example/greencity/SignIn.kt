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
        val collection = ConnectionDBUtil.getDB()

        this.nameSignIn = findViewById<EditText>(R.id.signin_nome)
        this.surnameSignIn = findViewById<EditText>(R.id.signin_cognome)
        this.emailSignIn = findViewById<EditText>(R.id.signin_email)
        this.passwordSignIn = findViewById<EditText>(R.id.signin_password)

        val users = mutableListOf<Document>()
        val register: Button = findViewById(R.id.signin_btn)

        register.setOnClickListener {
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
                            if(!emailDB.isEmpty()){
                                checkExistEmail(emailDB)
                            }
                        }
                        // of each document
                    }
                    // More code here
                }
            var isName = checkName()
            var isSurname = checkSurname()
            var isEmail = checkEmail()
            var isPw = checkPassword()
            if (isName && isSurname && isEmail && isPw) {
                sendUser(client, collection)
            }else{
                Log.i("ERRORLUCA",collection.toString())
            }
        }
    }

    private fun  isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

   private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun checkName(): Boolean{
        if (nameSignIn?.text.toString().trim().isEmpty()) {
            nameSignIn?.error = "Nome Obbligatorio"
            return false
        } else {
            nameSignIn?.error = null
            return true
        }
    }

    private fun checkSurname() : Boolean{
        if (surnameSignIn?.text.toString().trim().isEmpty()) {
            surnameSignIn?.error = "Cognome Obbligatorio"
            return false;
        } else {
            surnameSignIn?.error = null
            return true;
        }
    }

    private fun checkEmail(): Boolean {
        if (emailSignIn?.text.toString().trim().length == 0) {
            emailSignIn?.error = "Email Obbligatorio"
            return false
        } else {
            if (isEmailValid(emailSignIn?.text.toString())) {
                Toast.makeText(this, "Valid Email", Toast.LENGTH_LONG).show()
                emailSignIn?.error = null
                return true
            } else {
                emailSignIn?.error = "Inserire una mail valida"
                return false
            }
        }
    }

    private fun checkExistEmail(it: String): Boolean {
        if (emailSignIn?.text.toString().trim().equals(it)) {
            Log.i("EMAIL ESISTENTE", emailSignIn?.text.toString().trim())
            Toast.makeText(this, "Utente gia registrato con questa email", Toast.LENGTH_LONG).show()
            return false
        }
        else{
            return true
        }
    }

    private fun checkPassword() : Boolean{
        if (passwordSignIn?.text.toString().trim().length == 0) {
            passwordSignIn?.error = "Password Obbligatorio"
            return false
        } else {
            if (!isValidPassword(passwordSignIn?.text.toString())) {
                passwordSignIn?.error = "La password deve contenere 8 caratteri tra cui maiuscolo, minuscolo,numeri e caratteri speciali"
                return false
            }
            else{
                return true
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
            Log.i("STITCH", "inserito")
        }
    }
}
