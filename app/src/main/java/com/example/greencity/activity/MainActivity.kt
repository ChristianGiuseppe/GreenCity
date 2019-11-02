package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Regioni
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null
    private var btnLogin: Button? = null
    private var emailAccedi: EditText? = null
    private var passwordAccedi: EditText? = null
    private var layoutProgress: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.emailAccedi = findViewById(R.id.email_user)
        this.passwordAccedi = findViewById(R.id.signin_password)
        this.textSignIn = findViewById(R.id.sign_in)
        this.btnLogin = findViewById(R.id.login_btn)
        this.layoutProgress = findViewById(R.id.wrapper_progress_circular_signin)

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
            //VERIFICA CHE IL LOGIN E' STATO EFFETTUATO CORRETTAMENTE
            val iLogin = Intent(this, SplashGreenCity::class.java)
            startActivity(iLogin)
        }
    }
}
