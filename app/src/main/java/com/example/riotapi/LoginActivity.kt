package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar_login)
        progressBar.visibility = View.INVISIBLE
    }

    fun loginUser(view: View) {
        val email = findViewById<EditText>(R.id.editTextLoginEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextLoginPassword).text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG).show()
            return
        }
        progressBar.visibility = View.VISIBLE
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity) { task ->
            //checking if succesful
            if (task.isSuccessful) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
                finish()
            } else {
                val e = task.exception as FirebaseAuthException
                Toast.makeText(this@LoginActivity, "Login Failed: " + e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun goRegister (view: View){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }
}
