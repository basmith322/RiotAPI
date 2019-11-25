package com.example.riotapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
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

        /*val regions = resources.getStringArray(R.array.spnRegion)

        val spinner = findViewById<Spinner>(R.id.spinnerRegion)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, regions)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    when (position) {
                        1 -> Orianna.setDefaultRegion(Region.BRAZIL)
                        2 -> Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST)
                        3 -> Orianna.setDefaultRegion(Region.EUROPE_WEST)
                        4 -> Orianna.setDefaultRegion(Region.JAPAN)
                        5 -> Orianna.setDefaultRegion(Region.KOREA)
                        6 -> Orianna.setDefaultRegion(Region.LATIN_AMERICA_NORTH)
                        7 -> Orianna.setDefaultRegion(Region.LATIN_AMERICA_SOUTH)
                        8 -> Orianna.setDefaultRegion(Region.NORTH_AMERICA)
                        9 -> Orianna.setDefaultRegion(Region.OCEANIA)
                        10 -> Orianna.setDefaultRegion(Region.RUSSIA)
                        11 -> Orianna.setDefaultRegion(Region.TURKEY)
                        else -> {
                            Orianna.setDefaultRegion(Region.EUROPE_WEST)
                        }
                    }
                }
            }
        }*/

        val serverStatus = Orianna.shardStatusWithRegion(Region.EUROPE_WEST)
        textViewServerStatus.text = serverStatus.get().region.status.services[0].status.toString().toUpperCase()
    }
}


