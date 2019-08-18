package com.example.greencity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


class Navbar : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

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
        if (true)
            bottomNav?.inflateMenu(R.menu.nav_bottom_admin)
        else
            bottomNav?.inflateMenu(R.menu.nav_bottom_user)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.getItemId()) {
            R.id.home ->
                if (!bottomNav?.menu?.getItem(0)?.isChecked()!!)
                //loadFragment()
                    return true
            R.id.mappa_user ->
                if (!bottomNav?.menu?.getItem(1)?.isChecked()!!)
                //loadFragment()
                    return true
            R.id.report_user ->
                if (!bottomNav?.menu?.getItem(2)?.isChecked()!!)
                //loadFragment()
                    return true
            R.id.grafici ->
                if (!bottomNav?.menu?.getItem(1)?.isChecked()!!)
                //loadFragment()
                    return true

            R.id.report_admin ->
                if (!bottomNav?.menu?.getItem(1)?.isChecked()!!)
                //loadFragment()
                    return true
        }
        return false
    }
}
