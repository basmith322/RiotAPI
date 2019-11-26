package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_lookup_summoner.*
import org.joda.time.DateTime


class LookupSummonerActivity : AppCompatActivity(){
    private var summonerImage = ""
    private var summonerName = ""
    private var summonerID = ""
    private var summonerLevel = 0
    private var summonerTier = ""
    private var summonerDivision = ""
    private lateinit var lastUpdated: DateTime


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup_summoner)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))
        Orianna.setDefaultRegion(Region.EUROPE_WEST)
    }

    fun sendSummoner(view: View) {
        //Create summoner object and use summonerNamed method to obtain information about the player
        val summoner = Orianna.summonerNamed(editTextSummoner.text.toString()).get()

        if (summoner.id == null) {
            Toast.makeText(this, "No summoner found", Toast.LENGTH_LONG).show()
        }
        else {
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
            lastUpdated = summoner.updated

            val intent = Intent(this, DisplaySummonerActivity::class.java)
                    .putExtra("summonerName", summonerName)
                    .putExtra("summonerID", summonerID)
                    .putExtra("summonerLevel", summonerLevel)
                    .putExtra("lastUpdated", lastUpdated.toLocalDate().toString())
                    .putExtra("summonerImage", summonerImage)
                    .putExtra("summonerRank", "$summonerTier $summonerDivision")
            startActivity(intent)
        }
    }
}
