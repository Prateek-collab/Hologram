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

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                displayFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_search -> {
                displayFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_upload -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {
                displayFragment(NotificationsFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                displayFragment(ProfileFragment())
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

        displayFragment(HomeFragment())
    }

    private fun displayFragment(fragment:Fragment){
        val fragmentTrans = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.frame_layout,fragment)
        fragmentTrans.commit()
    }
}