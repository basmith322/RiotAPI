package com.example.riotapi.AdapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import com.merakianalytics.orianna.types.core.staticdata.Champion

class ImageAdapter : RecyclerView.Adapter<ImageHolder>() {

    private val championArray: Array<Champion> = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions.toTypedArray()

    override fun getItemCount(): Int {
        return this.championArray.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageUrl = championArray[position].image.url.replace("http", "https")
        holder.updateWithUrl(imageUrl)
        holder.updateWithCallback(championArray[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageItem = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageHolder(imageItem)
    }
}