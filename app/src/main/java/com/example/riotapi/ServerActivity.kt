package com.example.riotapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_server.*

class ServerActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))
        Orianna.setDefaultRegion(Region.EUROPE_WEST)

        val regions = resources.getStringArray(R.array.spnRegion)

        val spinner = findViewById<Spinner>(R.id.spinnerRegion)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, regions)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

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
                    val server = Orianna.getShardStatus()
                    textViewRegion.text = """${getString(R.string.txtRegion)}${server.regionTag}"""
                    textViewGameServerStatus.text = """${getString(R.string.txtServerStatus)} 
                        |${server.region.status.services[0].status.toUpperCase()}"""
                            .trimMargin()
                    textViewClientServerStatus.text = """${getString(R.string.txtClientStatus)} 
                        |${server.region.status.services[3].status.toUpperCase()}""".trimMargin()
                    textViewWebsiteServerStatus.text = """${getString(R.string.txtWebsiteStatus)} 
                        |${server.region.status.services[2].status.toUpperCase()}""".trimMargin()

                }
            }
        }
    }
}

