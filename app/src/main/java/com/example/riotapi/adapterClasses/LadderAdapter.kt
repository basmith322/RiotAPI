package com.example.riotapi.adapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.example.riotapi.Utilities.SetCurrentRegion
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Queue
import com.merakianalytics.orianna.types.core.league.LeagueEntry
import java.util.stream.Collectors

class LadderAdapter(position: Int) : RecyclerView.Adapter<LadderHolder>() {
    private val rankedList: List<LeagueEntry> = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO)
            .withRegion(SetCurrentRegion().setCurrentRegion(position))
            .get().stream()
            .sorted { o1, o2 -> o2.leaguePoints - o1.leaguePoints }
            .collect(Collectors.toList())
            .take(20)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LadderHolder {
        val rankedItem = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return LadderHolder(rankedItem)
    }

    override fun getItemCount(): Int {
        return this.rankedList.size
    }

    override fun onBindViewHolder(holder: LadderHolder, position: Int) {
        holder.updateWithText("Rank " + (position + 1) + " : "
                + rankedList[position].summoner.name + "\nLeague Points: "
                + rankedList[position].leaguePoints)
    }

}