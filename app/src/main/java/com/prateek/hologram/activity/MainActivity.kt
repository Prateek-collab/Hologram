package com.prateek.hologram.activity

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.prateek.hologram.R
import com.prateek.hologram.fragment.HomeFragment
import com.prateek.hologram.fragment.NotificationsFragment
import com.prateek.hologram.fragment.ProfileFragment
import com.prateek.hologram.fragment.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var textView:TextView
    internal lateinit var selectedFragment:Fragment

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                selectedFragment = HomeFragment()
            }
            R.id.nav_search -> {
                selectedFragment = SearchFragment()
            }
            R.id.nav_upload -> {
                textView.setText("Upload")
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                selectedFragment = NotificationsFragment()
            }
            R.id.nav_profile -> {
                selectedFragment = ProfileFragment()
            }
        }
        if (selectedFragment!=null)
        {
            supportFragmentManager.beginTransaction().replace(
                    R.id.frame_layout,
                    selectedFragment!!
            ).commit()
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        textView=findViewById(R.id.message)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().replace(
                R.id.frame_layout,
                HomeFragment()
        ).commit()
    }
}