package com.example.riotapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun goToLookup(view: View) {
        val intent = Intent(this, LookupSummonerActivity::class.java)
        startActivity(intent)
    }

    fun goToTop10(view: View) {
        //TODO
    }

    fun goToServerStatus(view: View)  {
        //TODO
    }

    fun goToMatchHistory(view: View) {
        //TODO
    }

    fun goToChampRotation(view: View) {
        //TODO
    }

    fun goToSummonerProfile(view: View) {
        //TODO
    }
}
