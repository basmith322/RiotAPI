package com.example.riotapi.adapterClasses

import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.riotapi.R
import com.example.riotapi.ui.ChampInfoActivity
import com.merakianalytics.orianna.types.core.staticdata.Champion
import com.squareup.picasso.Picasso

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myImageView: ImageView = itemView.findViewById(R.id.myImageView)

    //Loads the url into an image based on the string passed to it by the adapter
    fun updateWithUrl(url: String) {
        Picasso.get().load(url).into(myImageView)
    }

    //Loads all champion information based on the callback into the intent to be used byy the ChampInfoActivity
    fun updateWithCallback(champion: Champion) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ChampInfoActivity::class.java)
                    .putExtra("champName", "${champion.name} ${champion.title}")
                    .putExtra("champDetails", "Champion Lore: \n\n${champion.lore}")
                    .putExtra("champAbilities", "Champion Abilities: " +
                            "\n\nQ: ${champion.spells[0].name}:- " +
                            champion.spells[0].description +
                            "\n\nW: " + "${champion.spells[1].name}:- " +
                            champion.spells[1].description +
                            "\n\nE: ${champion.spells[2].name}:- " +
                            champion.spells[2].description +
                            "\n\nR: " + "${champion.spells[3].name}:- " +
                            champion.spells[3].description)
                    .putExtra("champImage", champion.image.url.replace("http", "https"))
            itemView.context.startActivity(intent)
        }
    }
}