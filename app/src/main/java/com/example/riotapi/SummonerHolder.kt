package com.example.riotapi

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.merakianalytics.orianna.types.core.match.MatchHistory

class SummonerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myItemView: TextView = itemView.findViewById(R.id.myTextView)

    fun updateWithCallback(matchHistory: MatchHistory) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MatchInfoActivity::class.java)
                    .putExtra("matchDuration", matchHistory)
        }
    }
}