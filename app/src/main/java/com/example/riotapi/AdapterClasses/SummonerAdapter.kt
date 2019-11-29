package com.example.riotapi.AdapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.core.match.Match

class SummonerAdapter: RecyclerView.Adapter<SummonerHolder>() {
    private val summoner = Orianna.summonerNamed("Ikbar").get()
    private val matchArray: Array<Match> = Orianna.matchHistoryForSummoner(summoner).get().toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummonerHolder {
        val summonerItem = LayoutInflater.from(parent.context).inflate(R.layout.summoner_item, parent, false)
        return SummonerHolder(summonerItem)
    }

    override fun getItemCount(): Int {
       return this.matchArray.size
    }

    override fun onBindViewHolder(holder: SummonerHolder, position: Int) {
        holder.updateWithText(matchArray[position].id.toString())
        holder.updateWithCallback(matchArray[position])
    }


}