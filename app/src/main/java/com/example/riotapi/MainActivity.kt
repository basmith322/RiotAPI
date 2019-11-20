package com.example.riotapi

import android.os.Bundle
import android.os.StrictMode
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}
