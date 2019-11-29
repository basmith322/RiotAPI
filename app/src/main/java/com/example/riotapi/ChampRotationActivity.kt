package com.example.riotapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_champ_rotation.*

class ChampRotationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champ_rotation)

        imageRecycler.layoutManager = GridLayoutManager(this, 3)
        imageRecycler.adapter = ImageAdapter()

    }
}
