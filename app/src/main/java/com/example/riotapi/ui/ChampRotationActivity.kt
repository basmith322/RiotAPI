package com.example.riotapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.riotapi.AdapterClasses.ImageAdapter
import com.example.riotapi.R
import kotlinx.android.synthetic.main.activity_champ_rotation.*

class ChampRotationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_rotation)

        imageRecycler.layoutManager = GridLayoutManager(this, 3)
        imageRecycler.adapter = ImageAdapter()

    }
}
