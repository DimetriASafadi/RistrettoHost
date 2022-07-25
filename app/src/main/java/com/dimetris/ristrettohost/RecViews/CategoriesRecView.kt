package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISCategory
import com.dimetris.ristrettohost.R

class CategoriesRecView(val data : ArrayList<RISCategory>, val context: Context) : RecyclerView.Adapter<CategoryViewHolder>() {

    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_category, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        if (selectedItem == position){
            holder.CateogoryName.setTextColor(context.resources.getColor(R.color.ris_white,null))
            holder.CateogoryName.setBackgroundResource(R.drawable.ris_radius_fill_light_green)
        }else{
            holder.CateogoryName.setTextColor(context.resources.getColor(R.color.ris_light_green,null))
            holder.CateogoryName.setBackgroundResource(R.drawable.ris_radius_stroke_light_green)
        }
        holder.CateogoryName.text = data[position].CatName

        holder.CateogoryName.setOnClickListener {
            selectedItem = position
            notifyDataSetChanged()
        }



    }
}
class CategoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val CateogoryName = view.findViewById<TextView>(R.id.CategoryName)


}