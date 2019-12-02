package com.example.riotapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.merakianalytics.orianna.types.core.staticdata.Map
import org.joda.time.DateTime
import org.joda.time.Duration

class MatchHistoryListActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_list)

        val matchDuration = intent.getSerializableExtra("matchDuration")
        val matchMap = intent.getSerializableExtra("matchMap")
        val matchCreated = intent.getSerializableExtra("matchCreated")

        findViewById<TextView>(R.id.textViewDuration).apply {
            if (matchDuration is Duration) text = "${getString(R.string.txtDuration)}: " +
                    "${matchDuration.standardMinutes}"
        }
        findViewById<TextView>(R.id.textViewMap).apply {
            if (matchMap is Map) text = "${getString(R.string.txtMap)}: " +
                    "${matchMap.name}"
        }
        findViewById<TextView>(R.id.textViewCreated).apply {
            if (matchCreated is DateTime) text = "${getString(R.string.txtCreated)}: " +
                    "${matchCreated.toLocalDate()}"
        }

    }
}
