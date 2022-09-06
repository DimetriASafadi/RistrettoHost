package com.dimetris.ristrettohost.CommonsSection

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.Constants.AppSPName
import com.dimetris.ristrettohost.CommonsSection.Constants.FireBaseKey
import com.dimetris.ristrettohost.CommonsSection.Constants.KeyAppLanguage
import com.dimetris.ristrettohost.CommonsSection.Constants.cartItems
import com.dimetris.ristrettohost.HostSection.MainScreen
import com.dimetris.ristrettohost.Models.RISCartItem
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
import kotlin.collections.ArrayList

class CommonFuncs {


    fun finishSplash(activity: Activity){
        Timer().schedule(object : TimerTask() {
            override fun run() {

                WriteOnSP(activity,"FirstTime","Passed")
                val intent = Intent(activity, MainScreen::class.java).apply {}
                activity.startActivity(intent)
                activity.finish()
            }
        }, 2000)
    }

    @Suppress("DEPRECATION")
    fun setLocale2(context: Activity, langua:String) {
        WriteOnSP(context,KeyAppLanguage,langua)
        //Log.e("Lan",session.getLanguage());
        val locale = Locale(langua)
        val config = Configuration(context.resources.configuration)
        Locale.setDefault(locale)
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        context.baseContext.resources.updateConfiguration(config,
            context.baseContext.resources.displayMetrics)
    }

    fun LanguageCheck(activity: Activity){
        if (IsInSP(activity,KeyAppLanguage)){
            setLocale2(activity,GetFromSP(activity,KeyAppLanguage)!!)
        }else{
            setLocale2(activity,"ar")
        }
    }

    fun GetFromSP(context: Context, key: String):String?{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "NoValue")
    }
    fun GetLongFromSP(context: Context, key: String):Long?{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        return sharedPreferences.getLong(key, 0)
    }
    fun WriteOnSP(context: Context, key: String, value: String){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }
    fun WriteLongOnSP(context: Context, key: String, value: Long){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
        editor.commit()
    }
    fun DeleteFromSP(context: Context, key: String){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
        editor.commit()
    }
    fun IsInSP(context: Context, key: String):Boolean{
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppSPName,
            Context.MODE_PRIVATE)
        return sharedPreferences.contains(key)
    }

    fun CheckCart(CounterTv: TextView){
        if (cartItems.size != 0){
            CounterTv.visibility = View.VISIBLE
            CounterTv.text = cartItems.size.toString()
        }else{
            CounterTv.visibility = View.GONE
        }
    }

    fun StoreCart(activity: Activity,data:ArrayList<RISCartItem>){
        val gson = Gson()
        val rawdata = gson.toJson(data).toString()
        Log.e("rawdata",rawdata)
        WriteOnSP(activity,"Cart",rawdata)
    }

    fun GetCart(activity: Activity){
        cartItems.clear()
        val gson = Gson()
        val rawdata = GetFromSP(activity,"Cart")
        if (rawdata != "NoValue"){
            cartItems.addAll(gson.fromJson(rawdata.toString(),Array<RISCartItem>::class.java).toList())
        }
    }


    fun getTotalOfOrder(theitems:ArrayList<RISCartItem>):Int{
        var result = 0
        for (item in theitems){
            result += item.ItemPrice?.CostValue!!
            if (item.ItemIsAdditionalCost){
                result += item.ItemAdditionalCost?.AddCostValue!!
            }
        }
        return result
    }

    fun CheckSelectedTime(theTime:String):String{
        val Timearr: List<String> = theTime.split(":")

        var theHour = Timearr[0].toInt()
        var theMinute = Timearr[1].toInt()
        var result = ""
        var readyhour = theHour.toString()
        var readyminute = theMinute.toString()


        Log.e("ReturnedHour", "$readyhour,$readyminute")
        var readyampm = "صباحاً"
        if (theMinute.toString().length == 1){
            readyminute = "0$theMinute"
        }

        if (theHour > 12){
            readyhour = (theHour - 12).toString()
            readyampm = "مساءاً"
        }
        if (theHour < 12){
            readyhour = theHour.toString()
            readyampm = "صباحاً"
        }
        if (theHour == 0){
            readyhour = "12"
            readyampm = "صباحاً"
        }
        if (theHour == 12){
            readyhour = "12"
            readyampm = "مساءاً"
        }
        if (readyhour.length == 1){
            Log.e("wtfHour",readyhour.toString())
            readyhour = "0$readyhour"
        }

        result = "$readyhour:$readyminute $readyampm"


        return result
    }

}