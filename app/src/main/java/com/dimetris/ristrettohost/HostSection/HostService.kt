package com.dimetris.ristrettohost.HostSection

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dimetris.ristrettohost.CasherSection.CasherMainScreen
import com.dimetris.ristrettohost.CasherSection.CasherService
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.R

class HostService: Service() {

    val commonFuncs = CommonFuncs()


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        intent.let {
            when(it.action){
                Constants.ACTION_SERVICE_START ->{
                    Log.e("ACTION_SERVICE_START","Service has Started")
                    CasherService.started.postValue(true)
                    startForeground(1441, getMyActivityNotification("اشعارات طلبات ريستريسو","لقد بدأت للتو جلسة اشعارات للطلبات"))
                }
                Constants.ACTION_SERVICE_STOP ->{
                    CasherService.started.postValue(false)
                    stopForegroundService()
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopForegroundService(){
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancel(
            1441
        )
        stopSelf()
    }

    override fun onDestroy() {
        commonFuncs.WriteOnSP(this,"ServiceStatus","OFF")
        super.onDestroy()
    }

    private fun getMyActivityNotification(contentTitle: String,contentText:String): Notification? {

        val notificationIntent = Intent(applicationContext, CasherMainScreen::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            this,
//            0, notificationIntent, 0
//        )
        var pendingIntent: PendingIntent? = null
        pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(applicationContext, 0, notificationIntent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(applicationContext, 0, notificationIntent, 0)
        }

        return NotificationCompat.Builder(applicationContext, Constants.CasherNotsChannel)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ris_ristresso_logo)
            .setContentIntent(pendingIntent)
            .setOngoing(false)
            .build()
    }
    private fun updateNotification(contentTitle:String,contentText:String) {
        val notification = getMyActivityNotification(contentTitle,contentText)
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1441, notification)
    }


}