package com.dimetris.ristrettohost.CasherSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.Models.RISCartItem
import com.dimetris.ristrettohost.Models.RISReadyOrder
import com.dimetris.ristrettohost.databinding.RisScreenCasherMainBinding
import com.google.firebase.database.*
import com.google.gson.Gson

class CasherMainScreen : AppCompatActivity() {

    lateinit var binding:RisScreenCasherMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenCasherMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        val RistressoDBRef: DatabaseReference = database.getReference("RistressoDB")
        val rawdata = RistressoDBRef.child("2022-08-27").child("1661616020714").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.key
                snapshot.value

                val gson = Gson()
                val orderDetails = gson.fromJson(snapshot.value.toString(),RISReadyOrder::class.java)
                Log.e("OrderId",orderDetails.OrderId.toString())
                Log.e("OrderDate",orderDetails.OrderDate.toString())
                Log.e("OrderTime",orderDetails.OrderTime.toString())
                Log.e("OrderTable",orderDetails.OrderTable.toString())
                Log.e("OrderDetails",orderDetails.OrderDetails.toString())


            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("error",error.toString())
            }

        })







    }
}