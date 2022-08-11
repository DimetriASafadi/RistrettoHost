package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.RecViews.PricesRecView
import com.dimetris.ristrettohost.databinding.RisScreenItemBinding

class ItemScreen : AppCompatActivity() {

    lateinit var selfData:RISItem

    lateinit var pricesRecView: PricesRecView
    val risCosts = ArrayList<RISCost>()

    lateinit var binding:RisScreenItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        selfData = intent.getSerializableExtra("selfData") as RISItem


        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.ItemName.text = selfData.ItemName
        binding.ItemDescription.text = if (!selfData.ItemDescription.isNullOrEmpty()) selfData.ItemDescription else "لا يوجد وصف"
        binding.ItemCategoryIcon.setImageResource(selfData.ItemCategory!!.CatIcon!!)
        binding.ItemCategoryName.text = selfData.ItemCategory!!.CatName!!

        risCosts.addAll(selfData.ItemPrice)
        pricesRecView = PricesRecView(risCosts,this)
        binding.ItemPrices.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.ItemPrices.adapter = pricesRecView

        if (selfData.ItemAdditionalCost == null){
            binding.ItemAddCostArea.visibility = View.GONE
        }else{
            binding.ItemAddCostArea.visibility = View.VISIBLE
            binding.ItemAdditionalCostDesc.text = selfData.ItemAdditionalCost!!.AddCostDesc
        }


    }
}