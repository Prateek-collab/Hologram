package com.prateek.hologram.activity


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.prateek.hologram.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var textView:TextView
    lateinit var fullName:EditText
    lateinit var userName:EditText
    lateinit var password:EditText
    lateinit var cpassword:EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fullName=findViewById(R.id.fullname_signUp)
        userName=findViewById(R.id.username_signUp)
        password=findViewById(R.id.username_password_signUp)
        cpassword=findViewById(R.id.username_confirm_password_signUp)

        textView = findViewById(R.id.signIn_signUp)
        textView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))

        }

        button=findViewById(R.id.btn_signUp)
        button.setOnClickListener {
            createAccount()
        }

    }

    private fun createAccount() {

        val Name=fullName.text.toString()
        val Username=userName.text.toString()
        val Pass=password.text.toString()
        val Confirmpass=cpassword.text.toString()

        when{
            TextUtils.isEmpty(Name) -> Toast.makeText(
                this,
                "Full Name cannot be empty",
                Toast.LENGTH_LONG
            )

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

            TextUtils.isEmpty(Confirmpass) -> Toast.makeText(
                this,
                "Please Confirm Your Password",
                Toast.LENGTH_LONG
            )

            (Pass!=Confirmpass) -> Toast.makeText(
                this,
                "Passwords Don't Match!",
                Toast.LENGTH_LONG
            )

            else->{

                val progressDialog=ProgressDialog(this@SignUpActivity)
                progressDialog.setTitle("Signing up")
                progressDialog.setMessage("This may take a while...")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth:FirebaseAuth=FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(Username,Pass)
                    .addOnCompleteListener { task->
                    if (task.isSuccessful){

                        saveUserInfo(Name,Username,Pass,Confirmpass,progressDialog)

                    }
                    else{

                        val message=task.exception!!.toString()
                        Toast.makeText(
                            this,
                            "Error $message",
                            Toast.LENGTH_LONG
                        )
                        mAuth.signOut()
                        progressDialog.dismiss()
                    }
                }
            }
        }
    }

    private fun saveUserInfo(name: String, username: String, pass: String, confirmpass: String, progressDialog:ProgressDialog) {

        val currentUserID= FirebaseAuth.getInstance().currentUser!!.uid
        val usersRef:DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap=HashMap<String,Any>()
        userMap["uid"]=currentUserID
        userMap["fullname"]=currentUserID
        userMap["username"]=currentUserID
        userMap["bio"]="Welcome to my hologram!"
        userMap["image"]="https://firebasestorage.googleapis.com/v0/b/hologram-app-10891.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=52e14046-454f-4795-88a7-1d7bfe666f89"

        usersRef.child(currentUserID).setValue(userMap)
            .addOnCompleteListener {task->

                if(task.isSuccessful){

                    progressDialog.dismiss()
                    Toast.makeText(
                        this,
                        "Account has been created successfully",
                        Toast.LENGTH_LONG
                    )

                    val intent= Intent(this@SignUpActivity,MainActivity::class.java)
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