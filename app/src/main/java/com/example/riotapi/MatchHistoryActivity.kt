package com.example.riotapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_match_history.*

class MatchHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        recyclerViewMatches.layoutManager = LinearLayoutManager(this)
        recyclerViewMatches.adapter = SummonerAdapter()
    }
}
