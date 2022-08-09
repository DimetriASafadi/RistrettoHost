package com.dimetris.ristrettohost.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.R

class ItemsListAdapter (private val context: Context, private val data: ArrayList<RISItem>): BaseAdapter() {

    var selectedCategory = 0

    override fun getCount(): Int {
        return data.count()
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val listheroView = LayoutInflater.from(context).inflate(R.layout.rec_item_home_item, parent, false)
        val WholeItem = listheroView.findViewById<LinearLayout>(R.id.WholeItem)
        val ItemCategoryIcon = listheroView.findViewById<ImageView>(R.id.ItemCategoryIcon)
        val ItemName = listheroView.findViewById<TextView>(R.id.ItemName)

        val theitem = data[position]

        ItemCategoryIcon.setImageResource(theitem.ItemCategory!!.CatIcon!!)
        ItemName.text = theitem.ItemName

        if (selectedCategory != 0){
            if (data[position].ItemCategory!!.CatId!! == selectedCategory){
                WholeItem.visibility = View.VISIBLE
            }else{
                WholeItem.visibility = View.GONE
            }
        }


        return listheroView

    }
}