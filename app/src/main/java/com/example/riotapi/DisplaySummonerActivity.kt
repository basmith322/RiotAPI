package com.example.riotapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplaySummonerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_summoner)

        val summonerName = intent.getStringExtra("summonerName")
        val summonerID = intent.getStringExtra("summonerID")
        val summonerLevel = intent.getIntExtra("summonerLevel",  0)
        /*val summonerRank = intent.getStringExtra("summonerRank")*/
        val summonerUpdated = intent.getStringExtra("lastUpdated")

        findViewById<TextView>(R.id.textViewSummonerName).apply {
            text = getString(R.string.txtSummonerName) + " " + summonerName
        }
        findViewById<TextView>(R.id.textViewSummonerID).apply {
            text = getString(R.string.txtSummonerID) + "" + summonerID
        }
        findViewById<TextView>(R.id.textViewSummonerLevel).apply{
            text = getString(R.string.txtSummonerLevel) + " " + summonerLevel
        }
/*        findViewById<TextView>(R.id.textViewSummonerRank).apply{
            text = getString(R.string.txtSummonerRank) + " " + summonerRank
        }*/
        findViewById<TextView>(R.id.textViewLastUpdated).apply{
            text = getString(R.string.txtLastUpdated) + " " + summonerUpdated
        }
    }
}
