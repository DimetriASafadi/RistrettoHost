package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.R

class ItemsRecView  (val data : ArrayList<RISItem>, val context: Context) : RecyclerView.Adapter<HomeItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        return HomeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_home_item, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {



//        holder.BannerTitle.text = data[position].ItemName



    }
}
class HomeItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
//    val BannerImage = view.findViewById<RoundedImageView>(R.id.BannerImage)
//    val BannerTitle = view.findViewById<TextView>(R.id.BannerTitle)


}