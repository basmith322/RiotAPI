package com.example.riotapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.joda.time.Duration

class MatchInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_info)

        val matchDuration = intent.getSerializableExtra("matchDuration")


        findViewById<TextView>(R.id.textViewMatch).apply {
            if (matchDuration is Duration) {
                text = matchDuration.standardMinutes.toString()
            }
        }
    }
}
