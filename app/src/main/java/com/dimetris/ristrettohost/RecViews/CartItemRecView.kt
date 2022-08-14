package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISCartItem
import com.dimetris.ristrettohost.R

class CartItemRecView (val data : ArrayList<RISCartItem>, val context: Context) : RecyclerView.Adapter<CItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CItemViewHolder {
        return CItemViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_cart_item, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: CItemViewHolder, position: Int) {



        holder.ItemName.text = data[position].ItemName
        holder.ItemPrice.text = "السعر الطبيعي : "+data[position].ItemPrice!!.CostName+" "+data[position].ItemPrice!!.CostValue+" شيكل"
        if (data[position].ItemIsAdditionalCost == null){
            holder.ItemAdditionalCost.text = "لا يوجد تكاليف إضافية"
        }else{
            if (data[position].ItemIsAdditionalCost){
                holder.ItemAdditionalCost.text = data[position].ItemAdditionalCost!!.AddCostDesc+" "+data[position].ItemAdditionalCost!!.AddCostValue+" شيكل"
                holder.ItemTotal.text = (data[position].ItemPrice!!.CostValue!!+data[position].ItemAdditionalCost!!.AddCostValue!!).toString()
            }else{
                holder.ItemAdditionalCost.text = "لا يوجد تكاليف إضافية"
            }
        }
        holder.ItemNotes.text = if (data[position].ItemNotes.isNullOrEmpty()) "لا يوجد أي ملاحظات" else data[position].ItemNotes
        holder.ItemNotes.text = data[position]?.ItemNotes ?:""


    }
}
class CItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val ItemName = view.findViewById<TextView>(R.id.ItemName)
    val ItemPrice = view.findViewById<TextView>(R.id.ItemPrice)
    val ItemAdditionalCost = view.findViewById<TextView>(R.id.ItemAdditionalCost)
    val ItemNotes = view.findViewById<TextView>(R.id.ItemNotes)
    val ItemTotal = view.findViewById<TextView>(R.id.ItemTotal)


}