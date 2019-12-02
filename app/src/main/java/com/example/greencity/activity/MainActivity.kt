package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.greencity.DBFirebase
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Regioni
import com.example.greencity.pojo.Utente
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R




class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null
    private var btnLogin: Button? = null
    private var emailAccedi: EditText? = null
    private var passwordAccedi: EditText? = null
    private var layoutProgress: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.greencity.R.layout.activity_main)

        this.emailAccedi = findViewById(com.example.greencity.R.id.email_user)
        this.passwordAccedi = findViewById(com.example.greencity.R.id.signin_password)
        this.textSignIn = findViewById(com.example.greencity.R.id.sign_in)
        this.btnLogin = findViewById(com.example.greencity.R.id.login_btn)
        this.layoutProgress = findViewById(com.example.greencity.R.id.wrapper_progress_circular_signin)

        textSignIn?.setOnClickListener {
            var list: ArrayList<Regioni?> = ArrayList()
            DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val snapshotIterator = dataSnapshot.child("regioni").children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var nextIt = iterator.next()
                        var reg: Regioni? = nextIt.getValue(Regioni::class.java)
                        list.add(reg)
                    }
                    val informazioniGenerali = InformazioniGenerali.getInformazioniGenerali()
                    informazioniGenerali.regioni = list
                    val iSignin = Intent(baseContext, SignIn::class.java)
                    startActivity(iSignin)
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

        }





        btnLogin?.setOnClickListener {
            DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val snapshotIterator = dataSnapshot.child("users").children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        var nextIt = iterator.next()

                        var users: Utente? = nextIt.getValue(Utente::class.java)
                        var keyUser: String? = nextIt.key
                        var emailEdit: String = emailAccedi?.text.toString()
                        var passwordEdit = passwordAccedi?.text.toString()
                        //VERIFICO CHE IL LOGIN E' STATO EFFETTUATO CORRETTAMENTE
                        if(emailEdit?.trim().length>0 || passwordEdit?.trim().length>0){
                            if(users?.email  == emailEdit.trim() && users?.password == passwordEdit.trim() ){
                                InformazioniGenerali.getInformazioniGenerali().user = users
                                InformazioniGenerali.getInformazioniGenerali().idUs = keyUser
                                val iLogin = Intent(this, SplashGreenCity::class.java)
                                startActivity(iLogin)
                                break
                            }else{
                                Toast.makeText(applicationContext,"Email o password non corrette",Toast.LENGTH_LONG).show()
                            }
                        }
                        else{
                            Toast.makeText(applicationContext,"Email e password obbligatori",Toast.LENGTH_LONG).show()
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })



        }
    }

    private fun Intent(valueEventListener: ValueEventListener, java: Class<SplashGreenCity>): Intent? {
        val iLogin = Intent(this, SplashGreenCity::class.java)
        return iLogin
    }
}
