package com.example.riotapi.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.SetCurrentRegion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region

class LinkedSummonerActivity : AppCompatActivity() {
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linked_summoner_activity)

        //Instantiate Database
        firebaseDatabase = FirebaseDatabase.getInstance()
        setServer()

    }

    fun basicWrite(view: View) {
        //Obtain values from the text field, load the spinner with objects and load the regions into the spinner at each position
        val summonerName = findViewById<TextView>(R.id.editTextSummonerProfileSetup).text.toString()
        val spinner = findViewById<Spinner>(R.id.spinnerNewSummoner)
        val region = spinner.selectedItemPosition

        //Perform validiation if the summoner name is empty and do not allow progress if it is
        if (TextUtils.isEmpty(summonerName)) {
            Toast.makeText(this, "Please enter your summoner name", Toast.LENGTH_LONG).show()
        } else {
            //Get instance of firebase based on current user. Write their summoner name and region to the database and load the menu
            val user = FirebaseAuth.getInstance().currentUser
            var uid = ""
            user?.let { uid = user.uid }
            firebaseDatabase.getReference(uid).child("summonerName").setValue(summonerName)
            firebaseDatabase.getReference(uid).child("region").setValue(region)
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to set the correct server based on the spinner menu
    private fun setServer() {
        val regions = resources.getStringArray(R.array.spnRegion)
        val spinner = findViewById<Spinner>(R.id.spinnerNewSummoner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, regions)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Orianna.setDefaultRegion(Region.BRAZIL)
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Orianna.setDefaultRegion(SetCurrentRegion().setCurrentRegion(position))
                }
            }
        }
    }
}