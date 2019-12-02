package com.example.riotapi.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.merakianalytics.orianna.types.core.staticdata.Map
import org.joda.time.Duration

class MatchHistoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_list)

        val matchDuration = intent.getSerializableExtra("matchDuration")
        val matchMap = intent.getSerializableExtra("matchMap")

        findViewById<TextView>(R.id.textViewDuration).apply {
            if (matchDuration is Duration) text = matchDuration.standardMinutes.toString()
        }
        findViewById<TextView>(R.id.textViewDuration).apply {
            if (matchMap is Map) text = matchMap.toString()
        }

    }
}
