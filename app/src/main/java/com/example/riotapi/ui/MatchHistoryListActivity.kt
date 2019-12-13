package com.example.riotapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.R
import com.example.riotapi.adapterClasses.MatchHistoryAdapter
import kotlinx.android.synthetic.main.activity_match_history_list.*

class MatchHistoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_list)
        val summonerName = intent.getStringExtra("summonerName")

        //Setup layoutManager to show in a 3 column grid and load the recyclerview with matches from the MatchesAdapter
        recyclerViewMatches.layoutManager = LinearLayoutManager(this)
        recyclerViewMatches.adapter = MatchHistoryAdapter(summonerName!!.toString())
    }
}
