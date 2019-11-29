package com.example.riotapi.AdapterClasses

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.example.riotapi.ui.MatchInfoActivity
import com.merakianalytics.orianna.types.core.match.Match

class SummonerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myItemView: TextView = itemView.findViewById(R.id.myTextView)

    fun updateWithText(match: String) {
        myItemView.apply { text = match }
    }

    fun updateWithCallback(match: Match) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MatchInfoActivity::class.java)
                    .putExtra("matchDuration", match.duration)
            itemView.context.startActivity(intent)
        }
    }
}