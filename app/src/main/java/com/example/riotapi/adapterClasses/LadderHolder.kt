package com.example.riotapi.adapterClasses

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R

class LadderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myItemView: TextView = itemView.findViewById(R.id.myTextView)

    //applys the text to the textitem
    fun updateWithText(league: String) {
        myItemView.apply { text = league }
    }
}