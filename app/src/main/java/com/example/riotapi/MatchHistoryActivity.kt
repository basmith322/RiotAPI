package com.example.riotapi

import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.common.Region
import com.merakianalytics.orianna.types.core.match.MatchHistory
import kotlinx.android.synthetic.main.activity_match_history.*

class MatchHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))
        Orianna.setDefaultRegion(Region.EUROPE_WEST)


        val summoner = Orianna.summonerNamed("Ikbar").get()
        val matchHistory = Orianna.matchHistoryForSummoner(summoner).get()[0]
        val matchString:String = matchHistory.duration.standardMinutes.toString() +
                matchHistory.map +
                matchHistory.mode +
                matchHistory.blueTeam.participants[0].champion.name

        findViewById<TextView>(R.id.textViewMatchHistory).apply {
            text = matchString
        }
    }
}
