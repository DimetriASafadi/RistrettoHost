package com.dimetris.ristrettohost.CasherSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.InterFaces.OnDateClick
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.RecViews.DateRecView
import com.dimetris.ristrettohost.RecViews.OrdersRecView
import com.dimetris.ristrettohost.databinding.RisScreenHistoryBinding
import com.google.firebase.database.*
import com.google.gson.Gson
import kotlin.collections.HashMap

class HistoryScreen : AppCompatActivity(), OnDateClick {


    lateinit var ShortHistoryRef: DatabaseReference
    lateinit var FulltHistoryRef: DatabaseReference

    lateinit var dateRecView:DateRecView
    val RisDates = ArrayList<String>()

    lateinit var ordersRecView:OrdersRecView
    val risReadyOrderShort = ArrayList<RISReadyOrderShort>()

    val wholeDataMap = HashMap<String,ArrayList<RISReadyOrderShort>>()

    lateinit var binding: RisScreenHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        ShortHistoryRef = database.getReference("RistressoDB").child("OrderHistoryShort")
        FulltHistoryRef = database.getReference("RistressoDB").child("OrderHistoryFull")

        dateRecView = DateRecView(RisDates,this,this)
        binding.OrdersDateRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.OrdersDateRecycler.adapter = dateRecView

        ordersRecView = OrdersRecView(risReadyOrderShort,this,FulltHistoryRef,ShortHistoryRef,0)
        binding.OrdersRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.OrdersRecycler.adapter = ordersRecView

        ShortHistoryRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val gson = Gson()
                    val td: HashMap<String, HashMap<String,String>>? = snapshot.value as HashMap<String, HashMap<String,String>>?
                    if (td != null) {
                        for ((key,value) in td.entries){
                            RisDates.add(key)
                            val temparr = ArrayList<RISReadyOrderShort>()
                            for ((keyv,valuev) in value){
                                temparr.add(gson.fromJson(valuev,RISReadyOrderShort::class.java))
                            }
                            temparr.sortByDescending { it.OrderId }
                            wholeDataMap[key] = temparr
                        }
                        RisDates.sort()
                        dateRecView.notifyDataSetChanged()
                        risReadyOrderShort.clear()
                        if (RisDates.size != 0){
                            risReadyOrderShort.addAll(wholeDataMap[RisDates[dateRecView.selectedItem]]!!)
                        }
                        ordersRecView.notifyDataSetChanged()

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistoryScreen, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
            }

        })

        binding.backBtn.setOnClickListener {
            finish()
        }



    }

    override fun OnDateClickListener(selecteditem: String) {
        risReadyOrderShort.clear()
        risReadyOrderShort.addAll(wholeDataMap[RisDates[dateRecView.selectedItem]]!!)
        ordersRecView.notifyDataSetChanged()
    }
}