package com.example.riotapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.AdapterClasses.MatchHistoryAdapter
import com.example.riotapi.R
import kotlinx.android.synthetic.main.activity_match_history_details.*

class MatchHistoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_details)
        val summonerName = intent.getStringExtra("summonerName")

        recyclerViewMatches.layoutManager = LinearLayoutManager(this)
        recyclerViewMatches.adapter = MatchHistoryAdapter(summonerName)
    }
}
