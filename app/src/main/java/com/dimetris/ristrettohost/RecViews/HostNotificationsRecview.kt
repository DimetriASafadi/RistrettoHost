package com.dimetris.ristrettohost.RecViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.Models.RISHostNotification
import com.dimetris.ristrettohost.R
import de.hdodenhof.circleimageview.CircleImageView

class HostNotificationsRecview(val data : ArrayList<RISHostNotification>, val context: Context) : RecyclerView.Adapter<HNotsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HNotsViewHolder {
        return HNotsViewHolder(LayoutInflater.from(context).inflate(R.layout.rec_item_host_notification, parent, false))    }



    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: HNotsViewHolder, position: Int) {

        if (data[position].NotOrderStatus == 0){
            holder.NotStatus.setImageResource(R.color.ris_grey)
        }else if (data[position].NotOrderStatus == 1){
            holder.NotStatus.setImageResource(R.color.ris_red_2)
        }else if (data[position].NotOrderStatus == 2){
            holder.NotStatus.setImageResource(R.color.ris_green)
        }
        holder.NotTime.text = data[position].NotTime
        holder.NotTitle.text = data[position].NotTitle
        holder.NotDesc.text = data[position].NotDescription


    }


}
class HNotsViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val NotStatus = view.findViewById<CircleImageView>(R.id.NotStatus)
    val NotTime = view.findViewById<TextView>(R.id.NotTime)
    val NotTitle = view.findViewById<TextView>(R.id.NotTitle)
    val NotDesc = view.findViewById<TextView>(R.id.NotDesc)


}