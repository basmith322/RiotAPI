package com.example.riotapi

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_ladder.*

class LadderActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ladder)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))

        progressBar = findViewById(R.id.progressBarRanked)
        progressBar.visibility = View.INVISIBLE

        rankedLadder()
    }

    private fun rankedLadder() {
        val regions = resources.getStringArray(R.array.spnRegion)
        val spinner = findViewById<Spinner>(R.id.spinnerRankedRegion)
        progressBar.visibility = View.VISIBLE

        //Create Array Adapter for spinner to use
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, regions)
            spinner.adapter = adapter

            //Set up on selected item listener
            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {

                //if no option is selected set default region to avoid crashing
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Orianna.setDefaultRegion(Region.EUROPE_WEST)
                }

                //Set the region based on which option is selected
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    when (position) {
                        0 -> Orianna.setDefaultRegion(Region.BRAZIL)
                        1 -> Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST)
                        2 -> Orianna.setDefaultRegion(Region.EUROPE_WEST)
                        3 -> Orianna.setDefaultRegion(Region.JAPAN)
                        4 -> Orianna.setDefaultRegion(Region.KOREA)
                        5 -> Orianna.setDefaultRegion(Region.LATIN_AMERICA_NORTH)
                        6 -> Orianna.setDefaultRegion(Region.LATIN_AMERICA_SOUTH)
                        7 -> Orianna.setDefaultRegion(Region.NORTH_AMERICA)
                        8 -> Orianna.setDefaultRegion(Region.OCEANIA)
                        9 -> Orianna.setDefaultRegion(Region.RUSSIA)
                        10 -> Orianna.setDefaultRegion(Region.TURKEY)
                        else -> {
                            Orianna.setDefaultRegion(Region.EUROPE_WEST)
                        }
                    }
                    val rankOne = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO)
                            .get()[0].summoner.name
                    textViewRankOne.text = rankOne.toString()

                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}

