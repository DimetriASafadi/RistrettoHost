package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.R

class PricesRecView(val data : ArrayList<RISCost>, val context: Context) : RecyclerView.Adapter<PriceViewHolder>() {


    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        return PriceViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_price, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {

        if (selectedItem == position){
            holder.PriceValue.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.ris_light_green))
        }else{
            holder.PriceValue.backgroundTintList = null
        }

        holder.PriceName.text = data[position].CostName
        holder.PriceValue.text = data[position].CostValue.toString() + " شيكل"

        holder.WholePrice.setOnClickListener {
            selectedItem = position
            notifyDataSetChanged()
        }


    }

    fun getSelectedItem():RISCost{
        return data[selectedItem]
    }
}
class PriceViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val WholePrice = view.findViewById<LinearLayout>(R.id.WholePrice)
    val PriceName = view.findViewById<TextView>(R.id.PriceName)
    val PriceValue = view.findViewById<TextView>(R.id.PriceValue)


}