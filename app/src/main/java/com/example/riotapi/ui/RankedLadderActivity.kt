package com.example.riotapi.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.R
import com.example.riotapi.adapterClasses.LadderAdapter
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

        setServerForLadder()
    }

    //Function to set the server based on the spinner
    private fun setServerForLadder() {
        val regions = resources.getStringArray(R.array.spnRegion)
        val spinner = findViewById<Spinner>(R.id.spinnerRankedRegion)
        progressBar.visibility = View.VISIBLE

        //Create Array Adapter for spinner to use
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, regions)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Orianna.setDefaultRegion(Region.EUROPE_WEST)
                }

                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View?, position: Int, id: Long) {
                    //When a region is selected, load the values from the LadderAdapter into the recyclerview
                    recyclerViewRanked.layoutManager = LinearLayoutManager(this@RankedLadderActivity)
                    recyclerViewRanked.adapter = LadderAdapter(position)
                    progressBar.visibility = View.GONE

                }
            }
        }
    }
}

