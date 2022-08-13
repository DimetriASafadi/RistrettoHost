package com.dimetris.ristrettohost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs

class SplashScreen : AppCompatActivity() {


    val commonFuncs = CommonFuncs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonFuncs.LanguageCheck(this)
        setContentView(R.layout.ris_screen_splash)



        commonFuncs.finishSplash(this)

    }
}