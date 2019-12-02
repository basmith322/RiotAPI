package com.example.riotapi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.squareup.picasso.Picasso
import kotlin.math.sqrt


class SummonerDetailsActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var sensor: Sensor
    private var shakeThresholdGravity: Float = 2f
    private var lastUpdateTime: Long = 0
    var summonerName: String = ""
    private lateinit var progressBar: ProgressBar

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summoner_details)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
        lastUpdateTime = System.currentTimeMillis()
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val summonerImage = intent.getStringExtra("summonerImage")
        summonerName = intent.getStringExtra("summonerName")
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
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        when (accuracy) {
            SensorManager.SENSOR_STATUS_UNRELIABLE -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_LOW -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM -> {
            }
            SensorManager.SENSOR_STATUS_ACCURACY_HIGH -> {
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values: FloatArray = event.values

        val x = values[0]
        val y = values[0]
        val z = values[1]

        val gX = x / SensorManager.GRAVITY_EARTH
        val gY = y / SensorManager.GRAVITY_EARTH
        val gZ = z / SensorManager.GRAVITY_EARTH

        val gForce: Float = sqrt(gX * gX + gY * gY + gZ * gZ)

        val currentTime:Long = System.currentTimeMillis()

        if (gForce >= shakeThresholdGravity) {
            if (currentTime - lastUpdateTime < 200) {
                return
            }
            lastUpdateTime = currentTime
            Toast.makeText(this, "Page was refreshed", Toast.LENGTH_LONG).show()
            finish()
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visibility = View.INVISIBLE
        sensorManager.registerListener(this, sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun summonerMatchHistory(view: View) {
        progressBar.visibility = View.VISIBLE
        val intent = Intent(this, MatchHistoryDetailsActivity::class.java)
                .putExtra("summonerName", summonerName)
        startActivity(intent)
    }
}
