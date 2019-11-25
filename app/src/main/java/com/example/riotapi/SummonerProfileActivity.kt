package com.example.riotapi

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase.getInstance

class SummonerProfileActivity : AppCompatActivity() {
    private lateinit var fbDatabaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_profile)

        fbDatabaseRef = getInstance().reference.child("summonerName")
    }

    fun basicRead(view: View) {

    }
}

