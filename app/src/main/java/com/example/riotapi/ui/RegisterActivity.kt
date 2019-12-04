package com.example.riotapi.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.CloseKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegisterActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Initialize Login Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        //Find the progress bar and hide it until necessary
        progressBar = findViewById(R.id.progressBar_reg)
        progressBar.visibility = View.INVISIBLE
    }

    fun registerUser(view: View) {
        val email = findViewById<EditText>(R.id.editTextRegEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextRegPassword).text.toString()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_LONG).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_LONG).show()
            return
        }
        CloseKeyboard().hideKeyboard(view)
        progressBar.visibility = View.VISIBLE

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@RegisterActivity) { task ->
                    //checking if successful
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivity, LinkedSummonerActivity::class.java))
                        finish()
                    } else {
                        val e = task.exception as FirebaseAuthException
                        Toast.makeText(this@RegisterActivity, "Registration Failed: " + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }
    fun goLogin(view: View) {
        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
    }
}
