package com.example.riotapi

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_server.*
import java.util.*

class ServerActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))

        progressBar = findViewById(R.id.progressBarServer)
        progressBar.visibility = View.INVISIBLE

        serverStatus()
    }

    private fun serverStatus() {
        //Initialize values for region and spinner and set the progress bar to be visible
        val regions = resources.getStringArray(R.array.spnRegion)
        val spinner = findViewById<Spinner>(R.id.spinnerRegion)
        progressBar.visibility = View.VISIBLE

        //Create Array Adapter for spinner to use
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, regions)
            spinner.adapter = adapter

            //Set up on selected item listener
            spinner.onItemSelectedListener = object :
                    OnItemSelectedListener {

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

                    //Initialize shardstatus object
                    val server = Orianna.getShardStatus()

                    //Create string values to store the results returned from the API call
                    val regionTag = "${getString(R.string.txtRegion)} ${server.regionTag}"

                    val gameStatus = ("${getString(R.string.txtGameStatus)} " +
                            server.region.status.services[0].status.toUpperCase(locale = Locale.UK)).trimMargin()

                    val clientStatus = ("${getString(R.string.txtClientStatus)} " +
                            server.region.status.services[3].status.toUpperCase(locale = Locale.UK)).trimMargin()

                    val websiteStatus = ("${getString(R.string.txtWebsiteStatus)} " +
                            server.region.status.services[2].status.toUpperCase(locale = Locale.UK)).trimMargin()

                    //Set texts in view to results of the function
                    textViewRegion.text = regionTag
                    textViewGameServerStatus.text = gameStatus
                    textViewClientServerStatus.text = clientStatus
                    textViewWebsiteServerStatus.text = websiteStatus

                    progressBar.visibility = View.GONE

                }
            }
        }
    }
}

