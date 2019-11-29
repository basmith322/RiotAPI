package com.example.riotapi

import android.app.Application
import android.os.StrictMode
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region

class RiotAPiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))
        Orianna.setDefaultRegion(Region.EUROPE_WEST)
    }
}