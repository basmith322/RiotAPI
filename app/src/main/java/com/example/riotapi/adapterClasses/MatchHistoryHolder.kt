package com.example.riotapi.adapterClasses

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.example.riotapi.ui.MatchHistoryListActivity
import com.merakianalytics.orianna.types.core.match.Match

class MatchHistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myItemView: TextView = itemView.findViewById(R.id.myTextView)

    fun updateWithText(match: String) {
        myItemView.apply { text = match }
    }

    fun updateWithCallback(match: Match) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, MatchHistoryListActivity::class.java)
                    .putExtra("matchDuration", match.duration)
                    .putExtra("matchMap", match.map.name)
                    .putExtra("matchCreated", match.creationTime)
                    .putExtra("matchSide", match.participants[0].team.side)
                    .putExtra("matchWinner", match.participants[0].team.isWinner)
                    .putExtra("championPlayed", match.participants[0].champion.name)
            itemView.context.startActivity(intent)
        }
    }
}