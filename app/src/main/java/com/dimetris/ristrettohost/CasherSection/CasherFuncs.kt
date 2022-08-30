package com.dimetris.ristrettohost.CasherSection

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.Models.RISReadyOrder
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.R
import com.dimetris.ristrettohost.RecViews.CartItemRecView
import com.dimetris.ristrettohost.databinding.RisDialogOrderDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.gson.Gson

class CasherFuncs {


    var orderDialog:Dialog? = null

    val commonFuncs = CommonFuncs()

    fun ShowOrderDetails(activity: Activity,snapshot: DataSnapshot){
        hideOrdersDialog()
        val gson = Gson()
        val selfdata = gson.fromJson(snapshot.value.toString(), RISReadyOrder::class.java)
        Log.e("selfdata",selfdata.toString())
        orderDialog = Dialog(activity)
        orderDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        orderDialog?.setCancelable(true)
        val dialogBinding = RisDialogOrderDetailsBinding.inflate(activity.layoutInflater)
        val view = dialogBinding.root
        orderDialog?.setContentView(view)
        dialogBinding.backBtn.setOnClickListener {
            hideOrdersDialog()
        }
        dialogBinding.OrderId.text = "رقم الطلب : "+selfdata.OrderId
        dialogBinding.OrderTotal.text = "اجمالي : "+commonFuncs.getTotalOfOrder(selfdata.OrderDetails)+" شيكل"
        dialogBinding.OrderDate.text = "تاريخ الطلب : "+selfdata.OrderDate
        dialogBinding.OrderTime.text = "وقت الطلب : "+selfdata.OrderTime
        val cartItemRecView = CartItemRecView(selfdata.OrderDetails,activity,null)
        dialogBinding.OrderItemsRecycler.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        dialogBinding.OrderItemsRecycler.adapter = cartItemRecView

        val window: Window = orderDialog?.window!!
        window.setBackgroundDrawable(
            ColorDrawable(activity.resources
                .getColor(R.color.tk_dialog_bg, null))
        )
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        orderDialog?.show()

    }

    fun hideOrdersDialog(){
        if (orderDialog != null){
            if (orderDialog?.isShowing!!){
                orderDialog?.dismiss()
                orderDialog = null
            }
        }
    }

}