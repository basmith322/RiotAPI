package com.example.riotapi.AdapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.core.league.LeagueEntry
import java.util.stream.Collectors

class LadderAdapter : RecyclerView.Adapter<LadderHolder>() {
    private val rankedArray: Array<LeagueEntry> = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO)
            .get().stream()
            .sorted { o1, o2 -> o2.leaguePoints - o1.leaguePoints }
            .collect(Collectors.toList())
            .toTypedArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LadderHolder {
        val rankedItem = LayoutInflater.from(parent.context).inflate(R.layout.summoner_item, parent, false)
        return LadderHolder(rankedItem)
    }

    override fun getItemCount(): Int {
        return this.rankedArray.size
    }

    override fun onBindViewHolder(holder: LadderHolder, position: Int) {
        holder.updateWithText(rankedArray[position].summoner.name)
    }

}