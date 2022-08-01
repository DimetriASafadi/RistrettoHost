package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.R

class PricesRecView(val data : ArrayList<RISCost>, val context: Context) : RecyclerView.Adapter<PriceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        return PriceViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_price, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {



//        holder.BannerTitle.text = data[position].ItemName



    }
}
class PriceViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
//    val PriceName = view.findViewById<TextView>(R.id.PriceName)
//    val PriceValue = view.findViewById<TextView>(R.id.PriceValue)


}