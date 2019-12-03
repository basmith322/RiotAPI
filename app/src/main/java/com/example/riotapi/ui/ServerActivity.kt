package com.example.riotapi.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.example.riotapi.Utilities.SetCurrentRegion
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import com.merakianalytics.orianna.types.core.status.ShardStatus
import kotlinx.android.synthetic.main.activity_server.*
import java.util.*

class ServerActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

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
                    android.R.layout.simple_spinner_dropdown_item, regions)
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
                                            view: View?, position: Int, id: Long) {

                    //Initialize shardstatus object
                    val server = ShardStatus.withRegion(SetCurrentRegion().setCurrentRegion(position)).get()

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

