package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun goToLookup(view: View) {
        val intent = Intent(this, LookupSummonerActivity::class.java)
        startActivity(intent)
    }

    fun goToTop10(view: View) {
        val intent = Intent(this, LadderActivity::class.java)
        startActivity(intent)
    }

    fun goToServerStatus(view: View)  {
        val intent = Intent(this, ServerActivity::class.java)
        startActivity(intent)
    }

    fun goToMatchHistory(view: View) {
        val intent = Intent(this, MatchHistoryActivity::class.java)
        startActivity(intent)
    }

    fun goToChampRotation(view: View) {
        val intent = Intent(this, ChampRotationActivity::class.java)
        startActivity(intent)
    }

    fun goToSummonerProfile(view: View) {
        val intent = Intent(this, SummonerProfileActivity::class.java)
        startActivity(intent)
    }

    fun signOut(view: View){
        firebaseAuth.signOut()
        Toast.makeText(this@MenuActivity, "Logout Successful", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@MenuActivity, LoginActivity::class.java))
    }
}
