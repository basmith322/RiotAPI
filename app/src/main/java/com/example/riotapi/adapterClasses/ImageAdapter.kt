package com.example.riotapi.adapterClasses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.merakianalytics.orianna.Orianna
import com.merakianalytics.orianna.types.common.Region
import com.merakianalytics.orianna.types.core.staticdata.Champion

class ImageAdapter : RecyclerView.Adapter<ImageHolder>() {

    //Create an array of type Champion to hold the free champions
    private val championArray: Array<Champion> = Orianna.championRotationWithRegion(Region.EUROPE_WEST).get().freeChampions.toTypedArray()

    //Returns the size of the array
    override fun getItemCount(): Int {
        return this.championArray.size
    }

    //Uses updateWithUrl to pass the url at the position to the viewholder. uses updateWithCallback to pass the position in the array to get the champion info
    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imageUrl = championArray[position].image.url.replace("http", "https")
        holder.updateWithUrl(imageUrl)
        holder.updateWithCallback(championArray[position])
    }

    //Tells the layout inflater to use imageItem when inflating the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val imageItem = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageHolder(imageItem)
    }
}