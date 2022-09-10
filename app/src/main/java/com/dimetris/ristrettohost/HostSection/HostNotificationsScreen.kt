package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.Constants
import com.dimetris.ristrettohost.Models.RISHostNotification
import com.dimetris.ristrettohost.Models.RISReadyOrderShort
import com.dimetris.ristrettohost.RecViews.HostNotificationsRecview
import com.dimetris.ristrettohost.databinding.RisScreenHostNotificationsBinding
import com.google.firebase.database.*
import com.google.gson.Gson
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HostNotificationsScreen : AppCompatActivity() {


    lateinit var HostNotHostorysRef:DatabaseReference
    private lateinit var datalistener: ValueEventListener

    val hostNots = ArrayList<RISHostNotification>()
    lateinit var hostNotificationsRecview: HostNotificationsRecview


    lateinit var binding:RisScreenHostNotificationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenHostNotificationsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val androidId = Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)
        binding.TodayDate.text = "اشعارات اليوم "+SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date())+"\n"+"جهاز رقم : "+androidId

        val database = FirebaseDatabase.getInstance(Constants.FireBaseKey)
        HostNotHostorysRef = database.getReference("RistressoDB").child("HostNotificationsHistory").child(androidId).child(
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date()))

        hostNotificationsRecview = HostNotificationsRecview(hostNots,this)
        binding.CurrentHNotsRecycler.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        binding.CurrentHNotsRecycler.adapter = hostNotificationsRecview

        datalistener = HostNotHostorysRef.addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    getNewData(snapshot)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HostNotificationsScreen, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
                Log.e("error",error.toString())
            }
        })












    }

    fun getNewData(snapshot: DataSnapshot):ArrayList<RISHostNotification>{
        val result = ArrayList<RISHostNotification>()
        try {
            hostNots.clear()
            hostNotificationsRecview.notifyDataSetChanged()
            val gson = Gson()
            val td: HashMap<String,String>? = snapshot.value as HashMap<String,String>?
            if (td != null) {
                Log.e("snapshot",snapshot.toString())
                for ((keyv,valuev) in td){
                    Log.e("entry",valuev.toString())
                    hostNots.add(gson.fromJson(valuev, RISHostNotification::class.java))
                }
                hostNots.sortByDescending { it.NotId }
            }
            hostNotificationsRecview.notifyDataSetChanged()


        }catch (error: JSONException){
            Toast.makeText(this, "حصل خطأ أثناء تحليل البيانات", Toast.LENGTH_SHORT).show()
        }
        return result
    }


}