package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewUserSummonerProfileActivity : AppCompatActivity() {
    private lateinit var firebaseDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_summoner_profile)

        //Instantiate Database
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("SummonerProfile")

        //Building the alert dialog
        AlertDialog.Builder(this)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Welcome to the Riot API App")
        builder.setMessage(R.string.txtNewSummoner)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton(R.string.skip) { dialog, which ->
            startActivity(Intent(this@NewUserSummonerProfileActivity,
                    MenuActivity::class.java))
            finish()
        }
        builder.show()
    }

    fun basicWrite(view: View) {
    }
}