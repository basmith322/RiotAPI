package com.example.riotapi

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_lookup_summoner.*

class LookupSummonerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup_summoner)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey("RGAPI-47860afe-dcc6-45f2-b30a-4bbe4428774c")
        Orianna.setDefaultRegion(Region.EUROPE_WEST)
    }

    fun sendSummoner(view: View) {
        //Create summoner object and use summonerNamed method to obtain information about the player
        val summoner = Orianna.summonerNamed(editTextSummoner.text.toString()).get()

        val summonerName = summoner.name
        val summonerID = summoner.id
        val summonerLevel = summoner.level
        val summonerRank = summoner.leaguePositions
        val lastUpdated = summoner.updated
        val intent = Intent(this, DisplaySummonerActivity::class.java)
                .putExtra("summonerName", summonerName)
                .putExtra("summonerID", summonerID)
                .putExtra("summonerLevel", summonerLevel)
                .putExtra("lastUpdated", lastUpdated)
         /*.putExtra("summonerRank", summonerRank)*/
        startActivity(intent)
    }

}
