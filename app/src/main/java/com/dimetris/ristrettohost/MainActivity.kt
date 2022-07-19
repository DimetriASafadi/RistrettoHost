package com.dimetris.ristrettohost

import android.content.ContentResolver
import android.content.Context
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.telephony.SubscriptionManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import pub.devrel.easypermissions.EasyPermissions
import java.util.*


class MainActivity : AppCompatActivity() {
    val SMS_PER_CODE = 9779
    val PHONE_STATUS_PER_CODE = 9889

    val PermissionsList = ArrayList<String>()
    val sMSReceiver = SMSReceiver()

    var cursor: Cursor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionsList.add(android.Manifest.permission.READ_SMS)
        PermissionsList.add(android.Manifest.permission.READ_PHONE_STATE)




        if (EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_SMS)){
//            enableLocationSettings()


            val myMessage: Uri = Uri.parse("content://sms/")
            val cr: ContentResolver = contentResolver
            cursor = cr.query(
                myMessage, arrayOf(
                    "_id",
                    "address", "date", "body", "read"
                ), null,
                null, null
            )
//            startManagingCursor(cursor)
            getSmsLogs(cursor!!, this)
            Log.e("lastSMSId", "$sms_num?")
            Log.e("lastSMSBody", "$sms_body?")

        }else{
            EasyPermissions.requestPermissions(
                this,
                "accept requests to get the wanted things ",
                SMS_PER_CODE,
                android.Manifest.permission.READ_SMS)
        }

        if (EasyPermissions.hasPermissions(this,android.Manifest.permission.RECEIVE_SMS)){

            val mIntentFilter = IntentFilter("sms_action")
            registerReceiver(sMSReceiver,mIntentFilter)


        }else{
            EasyPermissions.requestPermissions(
                this,
                "accept requests to get the wanted things ",
                PHONE_STATUS_PER_CODE,
                android.Manifest.permission.RECEIVE_SMS)
        }


        }

    val sms_num = ArrayList<String>()
    val sms_body = ArrayList<String>()


    fun getSmsLogs(c: Cursor, con: Context?) {
        if (sms_num.size > 0) {
            sms_num.clear()
            sms_body.clear()
        }
        try {
            if (c.moveToFirst()) {
                do {
                    Log.d(
                        "error", ""
                                + c.getString(
                            c
                                .getColumnIndexOrThrow("address")
                        )
                    )
                    if (c.getString(c.getColumnIndexOrThrow("address")) == null) {
                        c.moveToNext()
                        continue
                    }
                    val Number: String = c.getString(
                        c.getColumnIndexOrThrow("address")
                    ).toString()
                    val Body: String = c.getString(c.getColumnIndexOrThrow("address"))
                        .toString()
//                    sms_num.add(Number)
//                    sms_body.add(Body)
                } while (c.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(sMSReceiver)
    }

    override fun onStop() {
        super.onStop()
        cursor!!.close()
    }
}