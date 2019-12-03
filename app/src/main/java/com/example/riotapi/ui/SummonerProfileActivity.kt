package com.example.riotapi.ui


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.core.summoner.Summoner
import com.squareup.picasso.Picasso


class SummonerProfileActivity : AppCompatActivity() {
    private var summonerImage = ""
    private var summonerName = ""
    private var summonerID = ""
    private var summonerLevel = 0
    private var summonerTier = ""
    private var summonerDivision = ""
    private var summonerNameFromDB: String? = null
    private lateinit var summoner: Summoner
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_profile)

        basicRead()
    }

    private fun basicRead() {
        val user = FirebaseAuth.getInstance().currentUser
        var uid = ""
        user?.let { uid = user.uid }
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val dataMap: HashMap<String, Any> = dataSnapshot.value as HashMap<String, Any>
                    summonerNameFromDB = dataMap["summonerName"] as? String
                    getSummoner()
                    Toast.makeText(applicationContext, "SummonerName: $summonerNameFromDB", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //failed to read value
            }
        })
    }

    private fun getSummoner() {


        summoner = Orianna.summonerNamed(summonerNameFromDB).get()
        if (summoner.id == null) {
            Toast.makeText(this@SummonerProfileActivity, "No summoner found", Toast.LENGTH_LONG).show()
        } else {
            summonerImage = summoner.profileIcon.image.url.replace("http", "https")
            summonerName = summoner.name
            summonerID = summoner.id
            summonerLevel = summoner.level
            if (summoner.getLeaguePosition(Queue.RANKED_SOLO) == null) {
                summonerTier = "Unranked"
                summonerDivision = ""
            } else {
                summonerTier = summoner.getLeaguePosition(Queue.RANKED_SOLO).tier.toString()
                summonerDivision = summoner.getLeaguePosition(Queue.RANKED_SOLO).division.toString()
            }

            val lastUpdated = summoner.updated

            val imageView = findViewById<ImageView>(R.id.imageViewSummonerProfileIcon)
            Picasso.get().load(summonerImage).into(imageView)
            findViewById<TextView>(R.id.textViewSummonerProfileName).apply {
                text = summonerName
            }
            findViewById<TextView>(R.id.textViewSummonerProfileID).apply {
                text = summonerID
            }
            findViewById<TextView>(R.id.textViewSummonerProfileRank).apply {
                text = summonerTier + " " + summonerDivision
            }
            findViewById<TextView>(R.id.textViewSummonerProfileLevel).apply {
                text = summonerLevel.toString()
            }
            findViewById<TextView>(R.id.textViewProfileLastUpdated).apply {
                text = lastUpdated.toLocalDate().toString()
            }
        }
    }
}


