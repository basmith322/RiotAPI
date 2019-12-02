package com.example.riotapi.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.AdapterClasses.LadderAdapter
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_ranked_ladder.*

class RankedLadderActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranked_ladder)

        progressBar = findViewById(R.id.progressBarRanked)
        progressBar.visibility = View.INVISIBLE

        setServer()
    }

    private fun setServer() {
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
                    recyclerViewRanked.layoutManager = LinearLayoutManager(this@RankedLadderActivity)
                    recyclerViewRanked.adapter = LadderAdapter(position)
                    progressBar.visibility = View.GONE

                }
            }
        }
    }
}

