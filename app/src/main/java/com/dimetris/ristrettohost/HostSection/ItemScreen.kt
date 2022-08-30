package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants.cartItems
import com.dimetris.ristrettohost.Models.RISCartItem
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.RecViews.PricesRecView
import com.dimetris.ristrettohost.databinding.RisScreenItemBinding

class ItemScreen : AppCompatActivity() {

    val commonFuncs = CommonFuncs()

    lateinit var selfData:RISItem

    lateinit var pricesRecView: PricesRecView
    val risCosts = ArrayList<RISCost>()

    lateinit var binding:RisScreenItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonFuncs.LanguageCheck(this)
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

        binding.AddItem.setOnClickListener {
            if (binding.ItemIsAddCost.isChecked){
                cartItems.add(RISCartItem(selfData.ItemId,selfData.ItemName,pricesRecView.getSelectedItem(),selfData.ItemDescription,selfData.ItemCategory,true,selfData.ItemAdditionalCost,binding.ItemNotes.text.toString()))
                Toast.makeText(this, "تم إضافة عنصر إلى السلة", Toast.LENGTH_SHORT).show()
            }else{
                cartItems.add(RISCartItem(selfData.ItemId,selfData.ItemName,pricesRecView.getSelectedItem(),selfData.ItemDescription,selfData.ItemCategory,false,null,binding.ItemNotes.text.toString()))
                Toast.makeText(this, "تم إضافة عنصر إلى السلة", Toast.LENGTH_SHORT).show()
            }
            commonFuncs.StoreCart(this,cartItems)
            commonFuncs.GetCart(this)
            commonFuncs.CheckCart(binding.ItemsCartCount)
        }

        binding.ItemsCart.setOnClickListener {
            commonFuncs.OpenCartDialog(this,binding.ItemsCartCount)
        }

    }

    override fun onResume() {
        super.onResume()
        commonFuncs.LanguageCheck(this)
        commonFuncs.CheckCart(binding.ItemsCartCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        commonFuncs.hideLoadingDialog()
    }
}