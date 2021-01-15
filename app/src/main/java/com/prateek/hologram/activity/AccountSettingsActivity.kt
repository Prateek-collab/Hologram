package com.prateek.hologram.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.prateek.hologram.R

class AccountSettingsActivity : AppCompatActivity() {

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        button=findViewById(R.id.logout_accountsettings_btn)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)

        button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent= Intent(this@AccountSettingsActivity,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}