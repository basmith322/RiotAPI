package com.example.riotapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LinkedSummonerActivity : AppCompatActivity() {
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linked_summoner_activity)

        //Instantiate Database
        firebaseDatabase = FirebaseDatabase.getInstance()

        //Building the alert dialog
        AlertDialog.Builder(this)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome to the Riot API App")
        builder.setMessage(R.string.txtNewSummoner)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.skip) { dialog, which ->
            startActivity(Intent(this@LinkedSummonerActivity,
                    MenuActivity::class.java))
            finish()
        }
        builder.show()
    }

    fun basicWrite(view: View) {
        var summonerName = findViewById<TextView>(R.id.editTextSummonerProfileSetup).text.toString()
        val user = FirebaseAuth.getInstance().currentUser
        var uid = ""
        user?.let { uid = user.uid }
        firebaseDatabase.getReference(uid).child("summonerName").setValue(summonerName)
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }
}