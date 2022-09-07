package com.dimetris.ristrettohost.CasherSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.Models.RISReadyOrder
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.databinding.RisScreenNotificationBinding
import com.google.firebase.database.*
import com.google.gson.Gson

class NotificationScreen : AppCompatActivity() {



    lateinit var selfData:RISReadyOrder
    lateinit var FulltHistoryRef: DatabaseReference

    lateinit var binding:RisScreenNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val gson = Gson()
        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)

        val rawdata = intent.getStringExtra("rawdata")
        val readyData = gson.fromJson(rawdata.toString(), RISReadyOrderShort::class.java)




        FulltHistoryRef = database.getReference("RistressoDB").child("OrderHistoryFull")
        FulltHistoryRef.child(readyData.OrderDate).child(readyData.OrderId).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("snapshot",snapshot.toString())
                if (snapshot.exists()){
                    selfData = gson.fromJson(snapshot.value.toString(), RISReadyOrder::class.java)

                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NotificationScreen, "حصل خطأ أثناء عرض التفاصيل", Toast.LENGTH_SHORT).show()
            }

        })



    }
}