package com.example.riotapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        firebaseAuth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBarMenu)
        progressBar.visibility = View.INVISIBLE
    }

    fun goToLookup(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, LookupSummonerActivity::class.java)
        startActivity(intent)
    }

    fun goToRankedLadder(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, RankedLadderActivity::class.java)
        startActivity(intent)
    }

    fun goToServerStatus(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, ServerActivity::class.java)
        startActivity(intent)
    }

    fun goToChampRotation(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, ChampRotationActivity::class.java)
        startActivity(intent)
    }

    fun goToSummonerProfile(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, SummonerProfileActivity::class.java)
        startActivity(intent)
    }

    fun signOut(view: View) {
        progressBar.visibility = View.VISIBLE
        firebaseAuth.signOut()
        Toast.makeText(this@MenuActivity, "Logout Successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@MenuActivity, LoginActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.INVISIBLE
    }
}
