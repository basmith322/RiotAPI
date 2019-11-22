package com.example.riotapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.*
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase.*
import java.lang.String.valueOf
import kotlin.collections.HashMap

class SummonerProfileActivity : AppCompatActivity() {
    private lateinit var fbDatabaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_profile)

        fbDatabaseRef = FirebaseDatabase.getInstance().reference
    }

    fun basicRead(view: View) {
        fbDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val dataMap = dataSnapshot.value as HashMap<String, Any>
                    try {
                        val summonerString = String.valueOf((dataMap.get("email")))
                        makeText(applicationContext, "Summoner Name: " + summonerString, LENGTH_SHORT).show()
                    } catch (err: ClassCastException) {
                        makeText(applicationContext, "Error", LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
}
