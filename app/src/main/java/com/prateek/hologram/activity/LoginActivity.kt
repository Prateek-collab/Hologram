package com.prateek.hologram.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.prateek.hologram.R

class LoginActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textView=findViewById(R.id.signUp_login)
        textView.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
}