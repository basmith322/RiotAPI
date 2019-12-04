package com.example.riotapi.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.SetCurrentRegion
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.common.Region
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
    private var regionFromDB: Long? = null
    private lateinit var summoner: Summoner
    private lateinit var region: Region
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_profile)

        progressBar = findViewById(R.id.progressBarProfile)
        progressBar.visibility = View.VISIBLE

        basicRead()
    }

    private fun basicRead() {
        progressBar.visibility = View.VISIBLE
        val user = FirebaseAuth.getInstance().currentUser
        var uid = ""
        user?.let { uid = user.uid }
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val dataMap: HashMap<String, Any> = dataSnapshot.value as HashMap<String, Any>
                    summonerNameFromDB = dataMap["summonerName"] as? String
                    regionFromDB = dataMap["region"] as? Long
                    getSummoner()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                //failed to read value
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun getSummoner() {
        region = SetCurrentRegion().setCurrentRegion(regionFromDB!!.toInt())
        summoner = Orianna.summonerNamed(summonerNameFromDB).withRegion(region).get()
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
                text = "${getString(R.string.txtSummonerName)} $summonerName"
            }
            findViewById<TextView>(R.id.textViewSummonerProfileID).apply {
                text = "${getString(R.string.txtSummonerID)} $summonerID"
            }
            findViewById<TextView>(R.id.textViewSummonerProfileRank).apply {
                text = "${getString(R.string.txtSummonerRank)} $summonerTier $summonerDivision"
            }
            findViewById<TextView>(R.id.textViewSummonerProfileLevel).apply {
                text = "${getString(R.string.txtSummonerLevel)} $summonerLevel"
            }
            findViewById<TextView>(R.id.textViewProfileLastUpdated).apply {
                text = "${getString(R.string.txtLastUpdated)} ${lastUpdated.toLocalDate()}"
            }
        }
        progressBar.visibility = View.INVISIBLE
    }

    fun summonerMatchHistory(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, MatchHistoryDetailsActivity::class.java)
                .putExtra("summonerName", summonerName)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()

        progressBar.visibility = View.INVISIBLE
    }
}


