package com.example.riotapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import org.joda.time.DateTime
import org.joda.time.Duration

class MatchHistoryDetailsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_details)

        //Obtain the values from the MatchHistoryList activity
        val matchDuration = intent.getSerializableExtra("matchDuration")
        val matchMap = intent.getSerializableExtra("matchMap")
        val matchCreated = intent.getSerializableExtra("matchCreated")
        val matchSide = intent.getSerializableExtra("matchSide")
        val matchWinner = intent.getSerializableExtra("matchWinner")
        val championPlayed = intent.getSerializableExtra("championPlayed")

        //Place values into appropriate fields
        findViewById<TextView>(R.id.textViewDuration).apply {
            if (matchDuration is Duration) {
                text = "${getString(R.string.txtDuration)} ${matchDuration.standardMinutes} Minutes"
            }
        }
        findViewById<TextView>(R.id.textViewMap).apply {
            text = "${getString(R.string.txtMap)} $matchMap"
        }
        findViewById<TextView>(R.id.textViewCreated).apply {
            if (matchCreated is DateTime) {
                text = "${getString(R.string.txtCreated)} " +
                        "${matchCreated.toLocalDate()}"
            }
        }
        findViewById<TextView>(R.id.textViewSide).apply {
            text = "Side: $matchSide"
        }
        findViewById<TextView>(R.id.textViewChampionName).apply {
            text = "${getString(R.string.txtChampionName)} $championPlayed"
        }

        findViewById<TextView>(R.id.textViewWinner).apply {
            text = "Victory/Defeat: " + if (matchWinner == true) {
                "Victory"
            } else "Defeat"
        }
    }
}
