package com.example.jilinde
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import  androidx.appcompat.widget.Toolbar
import com.example.jilinde.CollectData
import com.example.jilinde.MainActivity
import com.example.jilinde.R

claHomepage : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer :DrawerLayout
    private  lateinit var toolbar: Toolbar
    private lateinit var navView:NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        //view identification
        drawer = findViewById(R.id.drawer_layout)
        toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navView =findViewById(R.id.nav_drawer)
        navView.setNavigationItemSelectedListener(this)
        //new instance of the action bar drawer toggleclass so that we can get the hamburger icon
        val toggle:ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        //attach a drawer listener for the toggle
        drawer.addDrawerListener(toggle)
        //sync state
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about -> {
                val intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
            }


            R.id.nav_maps -> {
                val intentMaps = Intent(this,MapsActivity2::class.java)
                startActivity(intentMaps)
            }
            R.id.nav_user_location -> {
                val intentUserLoc = Intent(this,MainActivity2::class.java)
                startActivity(intentUserLoc)
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}

