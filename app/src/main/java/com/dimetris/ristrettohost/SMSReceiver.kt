package com.dimetris.ristrettohost

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import java.util.*


class SMSReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {

        val extras: Bundle = p1?.extras!!
        if (extras != null) {
            if (extras.containsKey("slot")){
                Log.e("slot",extras.getInt("slot").toString() )
            }else if (extras.containsKey("simId")){
                Log.e("simId",extras.getInt("simId").toString() )
            }else if (extras.containsKey("sub_id")){
                Log.e("sub_id",extras.getInt("sub_id").toString() )
            }else if (extras.containsKey("subscription")){
                Log.e("subscription",extras.getInt("subscription").toString() )
            }else if (extras.containsKey("com.android.phone.extra.slot")){
                Log.e("com.android.phone.extra.slot",extras.getInt("com.android.phone.extra.slot").toString() )
            }else if (extras.containsKey("sim")){
                Log.e("sim",extras.getInt("sim").toString() )

            }

            Log.e("SIMDetection",detectSim(extras).toString())
        }

    }

    private fun detectSim(bundle: Bundle): String? {
        var slot = -1
        val keySet = bundle.keySet()
        for (key in keySet) {
            when (key) {

                "phone" ->{
                    slot = bundle.getInt("phone", -1)
                    Log.e("keyname", key.toString())
                }

                "slot" ->
                    {slot = bundle.getInt("slot", -1)
                        Log.e("keyname", key.toString())
                }
                "simId" ->
                {slot = bundle.getInt("simId", -1)
                    Log.e("keyname", key.toString())
                }
                "simSlot" ->
                {slot = bundle.getInt("simSlot", -1)
                    Log.e("keyname", key.toString())
                }
                "slot_id" ->
                {slot = bundle.getInt("slot_id", -1)
                    Log.e("keyname", key.toString())
                }
                "simnum" ->
                {slot = bundle.getInt("simnum", -1)
                    Log.e("keyname", key.toString())
                }
                "slotId" ->
                {slot = bundle.getInt("slotId", -1)
                    Log.e("keyname", key.toString())
                }
                "slotIdx" ->
                {slot = bundle.getInt("slotIdx", -1)
                    Log.e("keyname", key.toString())
                }
//                "subscription" -> slot = bundle.getInt("subscription", -1)
                "com.android.phone.extra.slot" ->
                {slot = bundle.getInt("com.android.phone.extra.slot", -1)
                    Log.e("keyname", key.toString())
                }
                else -> if (key.lowercase(Locale.getDefault()).contains("slot") or key.lowercase(
                        Locale.getDefault()
                    ).contains("sim")
                ) {
                    val value = bundle.getString(key, "-1")
                    if ((value == "0") or (value == "1") or (value == "2")) {
                        slot = bundle.getInt(key, -1)
                    }
                }
            }
        }

        Log.e("KKK", slot.toString())
        return if (slot == 0) {
            "sim1"
        } else if (slot == 1) {
            "sim2"
        } else {
            "undetected"
        }
    }




}


