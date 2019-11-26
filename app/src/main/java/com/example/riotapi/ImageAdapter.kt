package com.example.riotapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region

class ImageAdapter : RecyclerView.Adapter<ImageHolder>() {
    private val imageUrls: Array<String> = arrayOf(
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[0].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[1].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[2].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[3].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[4].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[5].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[6].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[7].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[8].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[9].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[10].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[11].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[12].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[13].image.url.replace("http", "https"),
            Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions[14].image.url.replace("http", "https")
    )

    override fun getItemCount(): Int {
        return this.imageUrls.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageUrl = imageUrls[position]
        holder.updateWithUrl(imageUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageItem = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageHolder(imageItem)
    }
}