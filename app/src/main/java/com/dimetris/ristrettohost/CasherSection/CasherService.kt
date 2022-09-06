package com.dimetris.ristrettohost.CasherSection

import android.app.*
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.CommonsSection.Constants.ACTION_SERVICE_START
import com.dimetris.ristrettohost.CommonsSection.Constants.ACTION_SERVICE_STOP
import com.dimetris.ristrettohost.CommonsSection.Constants.CasherNotsChannel
import com.dimetris.ristrettohost.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CasherService : Service() {



    val commonFuncs = CommonFuncs()


    companion object{
        val started = MutableLiveData<Boolean>()
    }
    private fun setInitialValues(){
        started.postValue(false)
    }


    override fun onCreate() {
        super.onCreate()
        Log.e("ServiceIssue","Service has Started")
        commonFuncs.WriteOnSP(this,"ServiceStatus","ON")
        setInitialValues()
        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        val ShortHistoryRef = database.getReference("RistressoDB").child("OrderHistoryShort")

        ShortHistoryRef.child("2022-08-31").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("onDataChange","yes")
                if (snapshot.exists()) {
                    updateNotification("آخر طلب لديك","تفاصيل الطلب هنا تفاصيل")
                    Log.e("snapshot.exists()","no notification")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CasherService, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
                Log.e("error",error.toString())
            }
        })

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        intent.let {
            when(it.action){
                ACTION_SERVICE_START ->{
                    Log.e("ACTION_SERVICE_START","Service has Started")
                    started.postValue(true)
                    startForeground(1441, getMyActivityNotification("اشعارات طلبات ريستريسو","لقد بدأت للتو جلسة اشعارات للطلبات"))
                }
                ACTION_SERVICE_STOP ->{
                    started.postValue(false)
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

        return NotificationCompat.Builder(applicationContext,CasherNotsChannel)
            .setContentTitle(contentTitle)
            .setContentText(contentText)
            .setSmallIcon(R.mipmap.ic_launcher)
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