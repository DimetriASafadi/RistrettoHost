package com.dimetris.ristrettohost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.HostSection.MainScreen

class SplashScreen : AppCompatActivity() {


    val commonFuncs = CommonFuncs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonFuncs.LanguageCheck(this)
        setContentView(R.layout.ris_screen_splash)

        if (commonFuncs.IsInSP(this,"FirstTime")){
            val intent = Intent(this, MainScreen::class.java).apply {}
            startActivity(intent)
            finish()
        }else{
            commonFuncs.finishSplash(this)
        }


    }
}