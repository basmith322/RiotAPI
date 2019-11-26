package com.example.riotapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ChampInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_info)

        val champName = intent.getStringExtra("champion")
        val champBlurb = intent.getStringExtra("champDetails")

        findViewById<TextView>(R.id.textViewChampName).apply {
            text = champBlurb
        }

    }
}
