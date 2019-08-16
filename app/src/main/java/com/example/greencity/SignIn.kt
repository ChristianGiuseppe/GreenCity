package com.example.greencity

import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import org.bson.Document
import com.mongodb.client.model.Projections
import com.mongodb.BasicDBObject
import com.mongodb.client.model.Accumulators.first
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Accumulators.first




class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        //Setup connectionMongoDB with Util Class
        val client = ConnectionDBUtil.defaultAppClient()
        val mongoClient = ConnectionDBUtil.getServiceClient()
        val collection = ConnectionDBUtil.getDB();
        //end setup

        val nameSignIn: TextView = findViewById(R.id.signin_nome) as EditText
        val surnameSignIn = findViewById(R.id.signin_cognome) as EditText;
        val emailSignIn = findViewById(R.id.signin_email) as EditText;
        val passwordSignIn = findViewById(R.id.signin_password) as EditText;
        val document = Document()
        val users = mutableListOf<Document>()


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
                            it.getString("email") // Extract only the 'email' field
                            Log.i("LUCA2",it.getString("email").toString())
                            if(emailSignIn.text.toString().trim() == it.getString("email")){
                                isValid = false;
                                Log.i("EMAIL ESISTENTE",emailSignIn.text.toString().trim())
                                Toast.makeText(this,"Utente gia registrato con questa email",Toast.LENGTH_LONG).show()
                            }
                        }
                        // of each document
                    }
                    // More code here
                }
            if (nameSignIn.text.toString().trim().length == 0) {
                nameSignIn.setError("Nome Obbligatorio");
                isValid = false;
            } else {
                nameSignIn.setError(null);
            }
            if (surnameSignIn.text.toString().trim().length == 0) {
                surnameSignIn.setError("Cognome Obbligatorio");
                isValid = false;
            } else {
                surnameSignIn.setError(null);
            }
            if (emailSignIn.text.toString().trim().length == 0) {
                emailSignIn.setError("Email Obbligatorio");
                isValid = false;
            } else {
               if(isEmailValid(emailSignIn.text.toString())){
                    Toast.makeText(this,"Valid Email",Toast.LENGTH_LONG).show()
                    emailSignIn.setError(null);
                }else{
                    isValid = false;
                    emailSignIn.setError("Inserire una mail valida");
                }
            }
            if (passwordSignIn.text.toString().trim().length == 0) {
                passwordSignIn.setError("Password Obbligatorio");
                isValid = false;
            } else {
                if(isValidPassword(passwordSignIn.text.toString())){
                    Toast.makeText(this,"Valid Password",Toast.LENGTH_LONG).show()
                    passwordSignIn.setError(null);
                }else{
                    isValid = false;
                    passwordSignIn.setError("La password deve contenere 8 caratteri tra cui maiuscolo, minuscolo,numeri e caratteri speciali");
                }
            }
            if (isValid) {
                document["nome"] = nameSignIn.text.toString();
                document["cognome"] = surnameSignIn.text.toString();
                document["email"] = emailSignIn.text.toString();
                document["password"] = passwordSignIn.text.toString();
                document["user_id"] = client.auth.user!!.id
                collection.insertOne(document).addOnSuccessListener {
                Log.d("STITCH", "inserito")
                }
            }
        }
    }

    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
