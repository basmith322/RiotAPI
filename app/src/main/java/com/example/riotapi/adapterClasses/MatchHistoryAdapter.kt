package com.example.riotapi.adapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.core.match.Match

class MatchHistoryAdapter(summonerName: String) : RecyclerView.Adapter<MatchHistoryHolder>() {
    //Create a summoner and make an array of matches based on the summoner.
    private val summoner = Orianna.summonerNamed(summonerName).get()
    private val matchArray: List<Match> = Orianna.matchHistoryForSummoner(summoner).get().take(20)

    //Tells the inflater to use summonerItem when inflating the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHistoryHolder {
        val summonerItem = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return MatchHistoryHolder(summonerItem)
    }

    //Returns the size of the array
    override fun getItemCount(): Int {
        return this.matchArray.size
    }

    //USes updateWithText function to pass the match date/time. Uses updateWithCallBack to pass the match position.
    override fun onBindViewHolder(holder: MatchHistoryHolder, position: Int) {
        holder.updateWithText(matchArray[position].creationTime.toLocalDate().toString())
        holder.updateWithCallback(matchArray[position])
    }


}