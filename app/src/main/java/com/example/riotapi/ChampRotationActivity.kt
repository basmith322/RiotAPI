package com.example.riotapi

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import kotlinx.android.synthetic.main.activity_champ_rotation.*

class ChampRotationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_rotation)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Orianna.setRiotAPIKey(getString(R.string.RiotAPiKey))
        Orianna.setDefaultRegion(Region.EUROPE_WEST)

        val freechamp1 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[0].name
        val freechamp2 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[1].name
        val freechamp3 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[2].name
        val freechamp4 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[3].name
        val freechamp5 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[4].name
        val freechamp6 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[5].name
        val freechamp7 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[6].name
        val freechamp8 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[7].name
        val freechamp9 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[8].name
        val freechamp10 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[9].name
        val freechamp11 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[10].name
        val freechamp12 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[11].name
        val freechamp13 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[12].name
        val freechamp14 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[13].name
        val freechamp15 = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[14].name

        textViewChamp1.text = freechamp1
        textViewChamp2.text = freechamp2
        textViewChamp3.text = freechamp3
        textViewChamp4.text = freechamp4
        textViewChamp5.text = freechamp5
        textViewChamp6.text = freechamp6
        textViewChamp7.text = freechamp7
        textViewChamp8.text = freechamp8
        textViewChamp9.text = freechamp9
        textViewChamp10.text = freechamp10
        textViewChamp11.text = freechamp11
        textViewChamp12.text = freechamp12
        textViewChamp13.text = freechamp13
        textViewChamp14.text = freechamp14
        textViewChamp15.text = freechamp15
    }
}
