package com.example.riotapi.AdapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.core.match.Match

class MatchHistoryAdapter(summonerName: String) : RecyclerView.Adapter<MatchHistoryHolder>() {
    private val summoner = Orianna.summonerNamed(summonerName).get()
    private val matchArray: Array<Match> = Orianna.matchHistoryForSummoner(summoner).get().toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHistoryHolder {
        val summonerItem = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return MatchHistoryHolder(summonerItem)
    }

    override fun getItemCount(): Int {
       return this.matchArray.size
    }

    override fun onBindViewHolder(holder: MatchHistoryHolder, position: Int) {
        holder.updateWithText(matchArray[position].creationTime.toLocalDate().toString())
        holder.updateWithCallback(matchArray[position])
    }


}