package com.dimetris.ristrettohost.RecViews

import android.app.Activity
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dimetris.ristrettohost.CasherSection.CasherFuncs
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.R
import com.google.firebase.database.*
import com.google.gson.Gson

class OrdersRecView (val data : ArrayList<RISReadyOrderShort>, val activity: Activity,val FullRef: DatabaseReference,val ShortRef:DatabaseReference,val purpose:Int) : RecyclerView.Adapter<OrderSViewHolder>() {

    val commonFuncs = CommonFuncs()
    val casherFuncs = CasherFuncs()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSViewHolder {
        return OrderSViewHolder(LayoutInflater.from(activity).inflate(R.layout.rec_item_short_order, parent, false))    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


    override fun onBindViewHolder(holder: OrderSViewHolder, position: Int) {
        holder.setIsRecyclable(false);

        Log.e("data",data.toString())
        val gson = Gson()
        holder.OrderId.text = "طلب رقم : "+data[position].OrderId
        holder.OrderTime.text = "وقت الطلب : "+commonFuncs.CheckSelectedTime(data[position].OrderTime)
        holder.OrderDate.text = "تاريخ الطلب : "+data[position].OrderDate
        holder.OrderTotal.text = "اجمالي : "+data[position].OrderTotal.toString()+" شيكل"
        holder.OrderDetails.setOnClickListener {
            FullRef.child(data[position].OrderDate).child(data[position].OrderId).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.e("snapshot",snapshot.toString())
                    if (snapshot.exists()){
                        casherFuncs.ShowOrderDetails(activity,snapshot)
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "حصل خطأ أثناء عرض التفاصيل", Toast.LENGTH_SHORT).show()
                }

            })
        }
        when (data[position].OrderStatus) {
            0 -> {
                holder.OrderPending.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_grey,null))
                holder.OrderPending.setTextColor(activity.resources.getColor(R.color.ris_white,null))
            }
            1 -> {
                holder.OrderDelete.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_red_2,null))
                holder.OrderDelete.setTextColor(activity.resources.getColor(R.color.ris_white,null))
            }
            2 -> {
                holder.OrderConfirm.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_green,null))
                holder.OrderConfirm.setTextColor(activity.resources.getColor(R.color.ris_white,null))
            }
        }
        if (purpose == 1){
            holder.OrderPending.setOnClickListener {
                data[position].OrderStatus = 0
                val rawdatashort = gson.toJson(data[position]).toString()
                ShortRef.child(data[position].OrderDate).child(data[position].OrderId).setValue(rawdatashort)
                holder.OrderPending.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_grey,null))
                holder.OrderPending.setTextColor(activity.resources.getColor(R.color.ris_white,null))
                holder.OrderDelete.backgroundTintList = null
                holder.OrderDelete.setTextColor(activity.resources.getColor(R.color.ris_red_2,null))
                holder.OrderConfirm.backgroundTintList = null
                holder.OrderConfirm.setTextColor(activity.resources.getColor(R.color.ris_green,null))
            }
            holder.OrderDelete.setOnClickListener {
                data[position].OrderStatus = 1
                val rawdatashort = gson.toJson(data[position]).toString()
                ShortRef.child(data[position].OrderDate).child(data[position].OrderId).setValue(rawdatashort)
                holder.OrderPending.backgroundTintList = null
                holder.OrderPending.setTextColor(activity.resources.getColor(R.color.ris_grey,null))
                holder.OrderDelete.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_red_2,null))
                holder.OrderDelete.setTextColor(activity.resources.getColor(R.color.ris_white,null))
                holder.OrderConfirm.backgroundTintList = null
                holder.OrderConfirm.setTextColor(activity.resources.getColor(R.color.ris_green,null))
            }
            holder.OrderConfirm.setOnClickListener {
                data[position].OrderStatus = 2
                val rawdatashort = gson.toJson(data[position]).toString()
                ShortRef.child(data[position].OrderDate).child(data[position].OrderId).setValue(rawdatashort)
                holder.OrderPending.backgroundTintList = null
                holder.OrderPending.setTextColor(activity.resources.getColor(R.color.ris_grey,null))
                holder.OrderDelete.backgroundTintList = null
                holder.OrderDelete.setTextColor(activity.resources.getColor(R.color.ris_red_2,null))
                holder.OrderConfirm.backgroundTintList = ColorStateList.valueOf(activity.resources.getColor(R.color.ris_green,null))
                holder.OrderConfirm.setTextColor(activity.resources.getColor(R.color.ris_white,null))
            }
        }else{

        }



    }
}
class OrderSViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val OrderId = view.findViewById<TextView>(R.id.OrderId)
    val OrderTime = view.findViewById<TextView>(R.id.OrderTime)
    val OrderDate = view.findViewById<TextView>(R.id.OrderDate)
    val OrderDetails = view.findViewById<TextView>(R.id.OrderDetails)
    val OrderTotal = view.findViewById<TextView>(R.id.OrderTotal)
    val OrderPending = view.findViewById<TextView>(R.id.OrderPending)
    val OrderDelete = view.findViewById<TextView>(R.id.OrderDelete)
    val OrderConfirm = view.findViewById<TextView>(R.id.OrderConfirm)



}