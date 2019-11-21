package com.example.riotapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.SyncTree

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
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

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
        var summonerName = findViewById<EditText>(R.id.editTextSummonerProfileSetup).text.toString()
        firebaseDatabase.child("summonerName").setValue(summonerName)

        startActivity(Intent(this@NewUserSummonerProfileActivity, LoginActivity::class.java))
        finish()
    }
}