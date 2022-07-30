package com.dimetris.ristrettohost.CommonsSection

import android.app.Activity
import android.content.Intent
import com.dimetris.ristrettohost.HostSection.MainScreen
import java.util.*

class CommonFuncs {

    fun finishSplash(activity: Activity){
        Timer().schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(activity, MainScreen::class.java).apply {}
                activity.startActivity(intent)
                activity.finish()
            }
        }, 2000)
    }

}