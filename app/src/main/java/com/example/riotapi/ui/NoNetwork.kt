package com.example.riotapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R

class NoNetwork : AppCompatActivity() {
    //Activity used to simply show if there is a network failure.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_network)
    }
}
