package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.HostSection.ItemScreen
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.R

class ItemsRecView(val data : ArrayList<RISItem>, val context: Context) : RecyclerView.Adapter<HomeItemViewHolder>() {


    var selectedCategory = 0
    var nameQuery = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        return HomeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_home_item, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.setIsRecyclable(false);

        if (selectedCategory != 0){
            if (!nameQuery.isNullOrEmpty()){
                if (data[position].ItemCategory!!.CatId!! == selectedCategory && data[position].ItemName!!.contains(nameQuery) ){
                    holder.WholeItem.visibility = View.VISIBLE
                }else{
                    holder.WholeItem.visibility = View.GONE
                }
            }else{
                if (data[position].ItemCategory!!.CatId!! == selectedCategory){
                    holder.WholeItem.visibility = View.VISIBLE
                }else{
                    holder.WholeItem.visibility = View.GONE
                }
            }

        }
        if (!nameQuery.isNullOrEmpty()){
            if (data[position].ItemName!!.contains(nameQuery)){
                if (selectedCategory != 0){
                    if (data[position].ItemCategory!!.CatId!! == selectedCategory){
                        holder.WholeItem.visibility = View.VISIBLE
                    }else{
                        holder.WholeItem.visibility = View.GONE
                    }
                }else{
                    holder.WholeItem.visibility = View.VISIBLE
                }
            }else{
                holder.WholeItem.visibility = View.GONE
            }
        }


        holder.ItemCategoryIcon.setImageResource(data[position].ItemCategory!!.CatIcon!!)
        holder.ItemName.text = data[position].ItemName

        holder.WholeItem.setOnClickListener {
            val toDetails = Intent(context,ItemScreen::class.java)
            toDetails.putExtra("selfData",data[position])
            context.startActivity(toDetails)
        }


    }
}
class HomeItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val WholeItem = view.findViewById<LinearLayout>(R.id.WholeItem)
    val ItemCategoryIcon = view.findViewById<ImageView>(R.id.ItemCategoryIcon)
    val ItemName = view.findViewById<TextView>(R.id.ItemName)


}