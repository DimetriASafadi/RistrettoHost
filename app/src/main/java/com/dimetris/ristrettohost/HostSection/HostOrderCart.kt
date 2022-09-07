package com.dimetris.ristrettohost.HostSection

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.Models.RISReadyOrder
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.R
import com.dimetris.ristrettohost.RecViews.CartItemRecView
import com.dimetris.ristrettohost.databinding.RisDialogCartBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class HostOrderCart {

    val commonFuncs = CommonFuncs()

    var cartDia: Dialog? = null


    fun OpenCartDialog(activity: Activity, CounterTv: TextView){
        hideLoadingDialog()
        cartDia = Dialog(activity)
        cartDia?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        cartDia?.setCancelable(true)
        val dialogBind = RisDialogCartBinding.inflate(activity.layoutInflater)
        val view = dialogBind.root
        cartDia?.setContentView(view)

        dialogBind.backBtn.setOnClickListener {
            hideLoadingDialog()
        }

        val cartItemRecView = CartItemRecView(Constants.cartItems,activity,CounterTv)
        dialogBind.CartItemsRecycler.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.VERTICAL,false)
        dialogBind.CartItemsRecycler.adapter = cartItemRecView

        dialogBind.SendOrder.setOnClickListener {
            val tablenumber = dialogBind.TableCounter.text.toString()
            if (tablenumber.isNullOrEmpty()){
                dialogBind.TableCounter.error = "أدخل رقم الطاولة"
                dialogBind.TableCounter.requestFocus()
                return@setOnClickListener
            }
            if (Constants.cartItems.size == 0){
                Toast.makeText(activity, "يجب عليك إضافة عناصر لأرسال طلب", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gson = Gson()
            val time = System.currentTimeMillis()
            val currentDate: String = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())
            val currentTime: String = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(Date())

            val androidId = Settings.Secure.getString(activity.contentResolver,
                Settings.Secure.ANDROID_ID)

            val theOrder = RISReadyOrder(time.toString(),currentTime,currentDate,tablenumber,androidId, Constants.cartItems)
            val theOrderShort = RISReadyOrderShort(time.toString(),currentTime,currentDate,tablenumber, 0,commonFuncs.getTotalOfOrder(
                Constants.cartItems
            ),androidId)
            val rawdata = gson.toJson(theOrder).toString()
            val rawdatashort = gson.toJson(theOrderShort).toString()


            val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
            val RistressoDBRef: DatabaseReference = database.getReference("RistressoDB")
            val LastOrderRef = database.getReference("RistressoDB").child("LastHostOrder")

            RistressoDBRef.child("OrderHistoryFull").child(currentDate).child(time.toString()).setValue(rawdata)
            RistressoDBRef.child("OrderHistoryShort").child(currentDate).child(time.toString()).setValue(rawdatashort)
            LastOrderRef.setValue(rawdatashort)


            Constants.cartItems.clear()
            cartItemRecView.notifyDataSetChanged()
            commonFuncs.StoreCart(activity, Constants.cartItems)
            commonFuncs.CheckCart(CounterTv)
        }

        val window: Window = cartDia?.window!!
        window.setBackgroundDrawable(
            ColorDrawable(activity.resources
                .getColor(R.color.ris_white, null))
        )
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        cartDia?.show()
    }
    fun hideLoadingDialog(){
        if (cartDia != null){
            if (cartDia?.isShowing!!){
                cartDia?.dismiss()
                cartDia = null
            }
        }
    }


}