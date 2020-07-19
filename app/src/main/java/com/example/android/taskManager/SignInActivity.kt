package com.example.android.taskManager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.android.taskManager.viewModel.SigninViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.email_edit_text
import kotlinx.android.synthetic.main.activity_signin.password_edit_text

class SignInActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private val viewModel by viewModels<SigninViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        observeAuthenticationState()
        signup_text.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
        }
        mAuth = FirebaseAuth.getInstance()
        login_btn.setOnClickListener {
            loginUser()
        }
    }

    private fun observeAuthenticationState() {
            viewModel.authenticationState.observe(this, Observer { authenticationState ->
                if (authenticationState == SigninViewModel.AuthenticationState.AUTHENTICATED) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }

    private fun loginUser() {
        val email = email_edit_text.text.toString()
        val password = password_edit_text.text.toString()

        if (email ==""){
            email_edit_text.error = "Email cannot be empty"
        }
        else if (password ==""){
            password_edit_text.error = "Password cannot be empty"
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        // User successfully signed in
                        Log.i(
                            "SignInActivity",
                            "Successfully signed in!")
                    }
                    else {
                        Toast.makeText(this,"Error message:" + task.exception!!.message.toString(),
                            Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
