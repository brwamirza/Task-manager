package com.brwakawa.android.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity: AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        signin_text.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        mAuth = FirebaseAuth.getInstance()
        signup_btn.setOnClickListener {
            signupUser()
        }

    }

    private fun signupUser() {
        val username = name_edit_text.text.toString()
        val email = email_edit_text.text.toString()
        val password = password_edit_text.text.toString()

        if (username ==""){
            name_edit_text.error = "Username cannot be empty"
        }
        else if (email ==""){
            email_edit_text.error = "Email cannot be empty"
        }
        else if (password ==""){
            password_edit_text.error = "Password cannot be empty"
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val id = mAuth.currentUser!!.uid.toString()
                        FirebaseDatabase.getInstance().reference.child("users").child(id).child("username").setValue(username)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this,"Error message:" + task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                    }

                }
        }

    }
}