package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

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

        progressBar.visibility = View.VISIBLE

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@MainActivity) { task ->
                    //checking if succesful
                    if (task.isSuccessful) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, NewUserSummonerProfileActivity::class.java))
                        finish()
                    } else {
                        val e = task.exception as FirebaseAuthException
                        Toast.makeText(this@MainActivity, "Registration Failed: " + e.message, Toast.LENGTH_SHORT).show()
                    }
                }
    }

    fun goLogin(view: View) {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
    }
}
