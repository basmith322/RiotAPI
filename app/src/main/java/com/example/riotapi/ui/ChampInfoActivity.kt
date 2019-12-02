package com.example.riotapi.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.riotapi.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_champ_info.view.*

class ChampInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_info)

        val champName = intent.getStringExtra("champName")
        val champBlurb = intent.getStringExtra("champDetails")
        val champAbilities = intent.getStringExtra("champAbilities")
        val champImage = intent.getStringExtra("champImage")

        findViewById<ImageView>(R.id.imageViewChampImage).apply {
            Picasso.get().load(champImage).into(imageViewChampImage)
        }
        findViewById<TextView>(R.id.textViewChampName).apply {
            text = champName
        }
        findViewById<TextView>(R.id.textViewChampBlurb).apply {
            text = champBlurb
        }
        findViewById<TextView>(R.id.textViewChampAbilities).apply {
            text = champAbilities
        }
    }
}