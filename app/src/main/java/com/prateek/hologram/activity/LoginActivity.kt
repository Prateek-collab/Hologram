package com.prateek.hologram.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.prateek.hologram.R

class LoginActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var userName:EditText
    lateinit var password:EditText
    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        textView=findViewById(R.id.signUp_login)
        button=findViewById(R.id.btn_login)
        userName=findViewById(R.id.username_login)
        password=findViewById(R.id.username_password)

        textView.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        button.setOnClickListener {
           loginUser()
        }

    }

    private fun loginUser() {
        val Username=userName.text.toString()
        val Pass=password.text.toString()

        when{
            TextUtils.isEmpty(Username) -> Toast.makeText(
                this,
                "Username cannot be empty",
                Toast.LENGTH_LONG
            )

            TextUtils.isEmpty(Pass) -> Toast.makeText(
                this,
                "Please Enter Your Password",
                Toast.LENGTH_LONG
            )

            else ->{
                val progressDialog= ProgressDialog(this@LoginActivity)
                progressDialog.setTitle("Logging in")
                progressDialog.setMessage("This may take a while...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(Username,Pass)
                    .addOnCompleteListener { task->
                        if (task.isSuccessful){
                            progressDialog.dismiss()
                            val intent= Intent(this@LoginActivity,MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                            }

                        else{
                            val message=task.exception!!.toString()
                            Toast.makeText(
                                this,
                                "Error $message",
                                Toast.LENGTH_LONG
                            )
                            FirebaseAuth.getInstance().signOut()
                            progressDialog.dismiss()
                        }
                    }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}