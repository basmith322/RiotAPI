package com.example.riotapi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.squareup.picasso.Picasso


class SummonerDetailsActivity : AppCompatActivity() {
    private lateinit var sensorManager: SensorManager
    private var summonerName: String = ""
    private lateinit var progressBar: ProgressBar


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_details)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        ShakeMethod().ShakeShakeShakeSenora(sensorManager)

        val summonerImage = intent.getStringExtra("summonerImage")
        summonerName = intent.getStringExtra("summonerName")!!.toString()
        val summonerID = intent.getStringExtra("summonerID")
        val summonerLevel = intent.getIntExtra("summonerLevel", 0)
        val summonerRank = intent.getStringExtra("summonerRank")
        val summonerUpdated = intent.getStringExtra("lastUpdated")

        val imageView = findViewById<ImageView>(R.id.imageViewSummoner)

        //Loading image using Picasso
        Picasso.get().load(summonerImage).into(imageView)

        findViewById<TextView>(R.id.textViewSummonerName).apply {
            text = """${getString(R.string.txtSummonerName)} $summonerName"""
        }
        findViewById<TextView>(R.id.textViewSummonerID).apply {
            text = """${getString(R.string.txtSummonerID)}$summonerID"""
        }
        findViewById<TextView>(R.id.textViewSummonerLevel).apply {
            text = """${getString(R.string.txtSummonerLevel)} $summonerLevel"""
        }
        findViewById<TextView>(R.id.textViewSummonerRank).apply {
            text = """${getString(R.string.txtSummonerRank)} $summonerRank"""
        }
        findViewById<TextView>(R.id.textViewLastUpdated).apply {
            text = """${getString(R.string.txtLastUpdated)} $summonerUpdated"""
        }
        progressBar = findViewById(R.id.progressBarSummonerResults)
        progressBar.visibility = View.INVISIBLE
    }

//    fun callback(): Void {
//        Toast.makeText(this, "Page was refreshed", Toast.LENGTH_LONG).show()
//        finish()
//        startActivity(intent)
//    }

    fun summonerMatchHistory(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, MatchHistoryDetailsActivity::class.java)
                .putExtra("summonerName", summonerName)
        startActivity(intent)
    }

//    override fun onPause() {
//        super.onPause()
//        sensorManager.unregisterListener(this)
//    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.INVISIBLE
    }
}
