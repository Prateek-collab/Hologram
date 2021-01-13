package com.prateek.hologram.activity

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.prateek.hologram.R

class MainActivity : AppCompatActivity() {

    private lateinit var textView:TextView

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                textView.setText("Home")
               return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                textView.setText("Search")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_upload -> {
                textView.setText("Upload")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                textView.setText("Notifications")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                textView.setText("Profile")
               return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        textView=findViewById(R.id.message)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}