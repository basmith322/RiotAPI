package com.example.riotapi

import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.merakianalytics.orianna.types.core.staticdata.Champion
import com.squareup.picasso.Picasso

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val myImageView: ImageView = itemView.findViewById(R.id.myImageView)

    fun updateWithUrl(url: String) {
        Picasso.get().load(url).into(myImageView)
    }

    fun updateWithCallback(champion: Champion) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ChampInfoActivity::class.java)
            intent.putExtra("champion", champion.name)
            intent.putExtra("champDetails", champion.blurb)
            itemView.context.startActivity(intent)
        }
    }
}
