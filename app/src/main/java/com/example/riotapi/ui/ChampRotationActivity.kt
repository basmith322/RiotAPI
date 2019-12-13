package com.example.riotapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.riotapi.R
import com.example.riotapi.adapterClasses.ImageAdapter
import kotlinx.android.synthetic.main.activity_champ_rotation.*

class ChampRotationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_rotation)

        //Setup layoutManager to show in a 3 column grid and load the recyclerview with images from the ImageAdapter
        imageRecycler.layoutManager = GridLayoutManager(this, 3)
        imageRecycler.adapter = ImageAdapter()

    }
}
