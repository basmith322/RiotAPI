package com.example.riotapi.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.CloseKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (isConnectedToNetwork()) {
            firebaseAuth = FirebaseAuth.getInstance()
            progressBar = findViewById(R.id.progressBar_login)
            progressBar.visibility = View.INVISIBLE

            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this@LoginActivity, NoNetwork::class.java))
        }
    }

    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    fun getValidationError(email: String, password: String): String? {
        if (email == "") {
            return "Please enter a valid email address"
        }
        if (password == "") {
            return "Please enter a valid password"
        }
        return null
    }

    fun loginUser(view: View) {
        val email = findViewById<EditText>(R.id.editTextLoginEmail).text.toString()
        val password = findViewById<EditText>(R.id.editTextLoginPassword).text.toString()
        val validationError = getValidationError(email, password)

        if (validationError != null) {
            Toast.makeText(this, validationError, Toast.LENGTH_LONG).show()
            return
        }

        CloseKeyboard().hideKeyboard(view)
        progressBar.visibility = View.VISIBLE

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity) { task ->
                    //checking if successful
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
                        finish()
                    } else {
                        val e = task.exception as FirebaseAuthException
                        Toast.makeText(this@LoginActivity, "Login Failed: " + e.message, Toast.LENGTH_SHORT).show()
                        progressBar.visibility = View.INVISIBLE
                    }
                }
    }

    fun goRegister(view: View) {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        if (isConnectedToNetwork()) {
            firebaseAuth = FirebaseAuth.getInstance()
            progressBar = findViewById(R.id.progressBar_login)
            progressBar.visibility = View.INVISIBLE

            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@LoginActivity, MenuActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this@LoginActivity, NoNetwork::class.java))
        }
    }
}
