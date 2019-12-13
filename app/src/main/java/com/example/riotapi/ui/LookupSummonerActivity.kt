package com.example.riotapi.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.CloseKeyboard
import com.example.riotapi.Utilities.SetCurrentRegion
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.common.Region
import com.merakianalytics.orianna.types.core.summoner.Summoner
import kotlinx.android.synthetic.main.activity_lookup_summoner.*
import org.joda.time.DateTime

class LookupSummonerActivity : AppCompatActivity() {
    private var summonerImage = ""
    private var summonerName = ""
    private var summonerID = ""
    private var summonerLevel = 0
    private var summonerTier = ""
    private var summonerDivision = ""
    private var currentRegion: Region? = null
    private lateinit var lastUpdated: DateTime
    private lateinit var progressBar: ProgressBar
    private lateinit var summoner: Summoner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookup_summoner)

        progressBar = findViewById(R.id.progressBarSummonerLoad)
        progressBar.visibility = View.INVISIBLE
        setServerForLookup()
    }

    //Take the summoner name and obtains their Name, ID, Level, Rank, Level and the last updated time
    fun sendSummoner(view: View) {
        summoner = Orianna.summonerNamed(editTextSummoner.text.toString()).withRegion(currentRegion).get()
        progressBar.visibility = View.VISIBLE

        if (summoner.id == null) {
            Toast.makeText(this@LookupSummonerActivity, "No summoner found", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.INVISIBLE
        } else {
            CloseKeyboard().hideKeyboard(view)

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

            //Put the values into the intent to be retrieved in SummonerDetailsActivity
            val intent = Intent(this@LookupSummonerActivity, SummonerDetailsActivity::class.java)
                    .putExtra("summonerName", summonerName)
                    .putExtra("summonerID", summonerID)
                    .putExtra("summonerLevel", summonerLevel)
                    .putExtra("lastUpdated", lastUpdated.toLocalDate().toString())
                    .putExtra("summonerImage", summonerImage)
                    .putExtra("summonerRank", "$summonerTier $summonerDivision")
            startActivity(intent)
        }
    }

    //Set the server based on the spinner value
    private fun setServerForLookup() {
        val regions = resources.getStringArray(R.array.spnRegion)
        val spinner = findViewById<Spinner>(R.id.spnRegionSummoner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, regions)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    currentRegion = Region.BRAZIL
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    currentRegion = SetCurrentRegion().setCurrentRegion(position)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.INVISIBLE
    }
}

