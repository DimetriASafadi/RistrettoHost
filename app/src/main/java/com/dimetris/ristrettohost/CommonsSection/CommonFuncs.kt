package com.dimetris.ristrettohost.CommonsSection

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import com.dimetris.ristrettohost.CommonsSection.Constants.AppSPName
import com.dimetris.ristrettohost.CommonsSection.Constants.KeyAppLanguage
import com.dimetris.ristrettohost.HostSection.MainScreen
import com.dimetris.ristrettohost.R
import java.util.*

class CommonFuncs {


    var cartDia: Dialog? = null


    fun finishSplash(activity: Activity){
        Timer().schedule(object : TimerTask() {
            override fun run() {
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


    fun OpenCartDialog(activity: Activity){
        hideLoadingDialog()
        cartDia = Dialog(activity)
        cartDia?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        cartDia?.setCancelable(false)
        cartDia?.setContentView(R.layout.ris_dialog_cart)
        val window: Window = cartDia?.window!!
        window.setBackgroundDrawable(
            ColorDrawable(activity.resources
                .getColor(R.color.tk_dialog_bg, null))
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