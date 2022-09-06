package com.dimetris.ristrettohost

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import com.dimetris.ristrettohost.CommonsSection.Constants.CasherNotsChannel


class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CasherNotsChannel,
                "Ristritto Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val soundUri: Uri = Uri.parse(
                "android.resource://" +
                        applicationContext.packageName +
                        "/" +
                        R.raw.orderrecieve
            )

            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            serviceChannel.enableLights(true);
            serviceChannel.enableVibration(true);
            serviceChannel.setSound(soundUri, attributes);
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
    }
}