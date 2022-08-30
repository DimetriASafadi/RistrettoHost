package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.InterFaces.OnDateClick
import com.dimetris.ristrettohost.R

class DateRecView (val data : ArrayList<String>, val context: Context,val onDateClick: OnDateClick) : RecyclerView.Adapter<DateViewHolder>() {


    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_date, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {

        holder.WholeDate.text = data[position]
        if (selectedItem == position){
            holder.WholeDate.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.ris_light_green))
            holder.WholeDate.setTextColor(context.getColor(R.color.ris_white))
        }else{
            holder.WholeDate.backgroundTintList = null
            holder.WholeDate.setTextColor(context.getColor(R.color.ris_light_green))
        }
        holder.WholeDate.setOnClickListener {
            selectedItem = position
            onDateClick.OnDateClickListener(data[position])
            notifyDataSetChanged()
        }


    }

}
class DateViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val WholeDate = view.findViewById<TextView>(R.id.WholeDate)


}