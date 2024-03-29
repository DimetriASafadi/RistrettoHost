package com.dimetris.ristrettohost.CasherSection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.CommonsSection.Constants.ACTION_SERVICE_START
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.RecViews.OrdersRecView
import com.dimetris.ristrettohost.databinding.RisScreenCasherMainBinding
import com.google.firebase.database.*
import com.google.gson.Gson
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*

class CasherMainScreen : AppCompatActivity() {

    val commonFuncs = CommonFuncs()
    val casherFuncs = CasherFuncs()

    lateinit var ordersRecView:OrdersRecView
    val risReadyOrderShort = ArrayList<RISReadyOrderShort>()

    lateinit var ShortHistoryRef:DatabaseReference
    lateinit var FulltHistoryRef:DatabaseReference
    lateinit var HostNotificationsRef:DatabaseReference
    lateinit var HostNotHostorysRef:DatabaseReference

    private lateinit var datalistener: ValueEventListener

    lateinit var binding:RisScreenCasherMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonFuncs.LanguageCheck(this)
        binding = RisScreenCasherMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        ShortHistoryRef = database.getReference("RistressoDB").child("OrderHistoryShort")
        FulltHistoryRef = database.getReference("RistressoDB").child("OrderHistoryFull")
        HostNotificationsRef = database.getReference("RistressoDB").child("HostNotifications")
        HostNotHostorysRef = database.getReference("RistressoDB").child("HostNotificationsHistory")
        ordersRecView = OrdersRecView(risReadyOrderShort,this,FulltHistoryRef,ShortHistoryRef,HostNotificationsRef,HostNotHostorysRef,1)
        binding.CurrentOrdersRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.CurrentOrdersRecycler.adapter = ordersRecView
        datalistener = ShortHistoryRef.child(SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    getNewData(snapshot)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@CasherMainScreen, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
                Log.e("error",error.toString())
            }
        })
        binding.ItemsCart.setOnClickListener {
            startActivity(Intent(this,HistoryScreen::class.java))
        }
        if (commonFuncs.GetFromSP(this,"ServiceStatus") != "ON"){
            Intent(
                this,
                CasherService::class.java
            ).apply {
                this.action = ACTION_SERVICE_START
                startService(this)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        casherFuncs.hideOrdersDialog()
    }
    fun getNewData(snapshot: DataSnapshot):ArrayList<RISReadyOrderShort>{
        val result = ArrayList<RISReadyOrderShort>()
        try {
            risReadyOrderShort.clear()
            ordersRecView.notifyDataSetChanged()
            val gson = Gson()
            val td: HashMap<String,String>? = snapshot.value as HashMap<String,String>?
            if (td != null) {
                Log.e("snapshot",snapshot.toString())
                for ((keyv,valuev) in td){
                    Log.e("entry",valuev.toString())
                    risReadyOrderShort.add(gson.fromJson(valuev,RISReadyOrderShort::class.java))
                }
                risReadyOrderShort.sortByDescending { it.OrderId }
            }
            ordersRecView.notifyDataSetChanged()


        }catch (error:JSONException){
            Toast.makeText(this, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
        }
        return result
    }
}