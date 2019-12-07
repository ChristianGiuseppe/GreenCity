package com.example.greencity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.greencity.DBFirebase
import com.example.greencity.R
import com.example.greencity.fragment.*
import com.example.greencity.pojo.InformazioniGenerali
import com.example.greencity.pojo.Markers
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class Navbar : AppCompatActivity() {

    private var bottomNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navbar)
        initBar()
    }

    //aggiungere il ruolo all'utente se il suo ruolo è admin allora si carica il menu admin altrimenti si carica il menu dell'user
    private fun initBar() {
        bottomNav = findViewById(R.id.bottom_navigation_bar)
        bottomNav?.menu?.clear()
        var isValid = false
        DBFirebase.getDbFirebase().databaseReference.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var countChildren : Long = 0
                val snapshotIterator = dataSnapshot.child("users").child(InformazioniGenerali.getInformazioniGenerali().idUs).children
                val iterator = snapshotIterator.iterator()
                while (iterator.hasNext()) {
                    countChildren++
                    var nextIt = iterator.next()
                    var keyAdmin = nextIt.key?.equals("admin")
                    if(keyAdmin == true){
                       var valAdmin = nextIt.value?.equals("true")
                        if(valAdmin == true){
                            isValid = true

                        }
                    }
                    if (isValid){
                        bottomNav?.inflateMenu(R.menu.nav_bottom_admin)
                        break
                    }

                    else{
                        bottomNav?.inflateMenu(R.menu.nav_bottom_user)
                        bottomNav?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
                        loadFragment(Home())
                        break
                    }

                }
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.fragments_container, fragment)
        transaction.commit()
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                if (!bottomNav?.menu?.getItem(0)?.isChecked()!!)
                    loadFragment(Home())
                return@OnNavigationItemSelectedListener true
            }
            R.id.mappa_user -> {
                if (!bottomNav?.menu?.getItem(1)?.isChecked()!!)
                    loadFragment(MapsUser())
                return@OnNavigationItemSelectedListener true
            }
            R.id.report_user -> {
                if (!bottomNav?.menu?.getItem(2)?.isChecked()!!)
                    loadFragment(ReportUser())
                return@OnNavigationItemSelectedListener true
            }
            R.id.grafici -> {
                if (!bottomNav?.menu?.getItem(1)?.isChecked()!!)
                    loadFragment(Graph())
                return@OnNavigationItemSelectedListener true
            }
            R.id.report_admin -> {
                if (!bottomNav?.menu?.getItem(2)?.isChecked()!!)
                    loadFragment(ReportAdmin())
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }
}
