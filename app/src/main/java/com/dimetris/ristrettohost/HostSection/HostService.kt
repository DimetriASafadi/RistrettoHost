package com.dimetris.ristrettohost.HostSection

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.dimetris.ristrettohost.CasherSection.CasherMainScreen
import com.dimetris.ristrettohost.CasherSection.CasherService
import com.dimetris.ristrettohost.CasherSection.NotificationScreen
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.Models.RISHostNotification
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.R
import com.google.firebase.database.*
import com.google.gson.Gson

class HostService: Service() {

    val commonFuncs = CommonFuncs()

    lateinit var HostNotRef:DatabaseReference
    lateinit var tempolistener:ValueEventListener

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object{
        val hstarted = MutableLiveData<Boolean>()
    }
    private fun setInitialValues(){
        hstarted.postValue(false)
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("ServiceIssue","Service has Started")
        commonFuncs.WriteOnSP(this,"ServiceStatus","ON")
        setInitialValues()
        val gson = Gson()
        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        val androidId = Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)

        HostNotRef = database.getReference("RistressoDB").child("HostNotifications").child(androidId)

        tempolistener = HostNotRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("onDataChange","yes")
                if (snapshot.exists()) {
                    val readyData = gson.fromJson(snapshot.value.toString(), RISHostNotification::class.java)
                    updateNotification(readyData.NotTitle.toString(),readyData.NotDescription.toString())
                    Log.e("snapshot.exists()","no notification")
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HostService, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
                Log.e("error",error.toString())
            }
        })
    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action){
                Constants.H_ACTION_SERVICE_START ->{
                    Log.e("ACTION_SERVICE_START","Service has Started")
                    CasherService.started.postValue(true)
                    startForeground(9999, getMyActivityNotification("الاشعارت قيد العمل","اضغط هنا لعرض آخر الاشعارات الخاصة بلطلباتك"))
                }
                Constants.H_ACTION_SERVICE_STOP ->{
                    CasherService.started.postValue(false)
                    stopForegroundService()
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopForegroundService(){
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).cancelAll()
        HostNotRef.removeEventListener(tempolistener)
        stopSelf()
    }

    override fun onDestroy() {
        Log.e("onDestroy","Service has been destroyed")
        commonFuncs.WriteOnSP(this,"ServiceStatus","OFF")
        super.onDestroy()
    }

    private fun getMyActivityNotification(contentTitle: String,contentText:String): Notification? {

        val notificationIntent = Intent(applicationContext, HostNotificationsScreen::class.java)


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