package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.Models.RISCategory
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.R
import com.dimetris.ristrettohost.RecViews.CategoriesRecView
import com.dimetris.ristrettohost.RecViews.ItemsRecView
import com.dimetris.ristrettohost.databinding.RisScreenMainBinding

class MainScreen : AppCompatActivity() {

    lateinit var categoriesRecView: CategoriesRecView
    val risCategories = ArrayList<RISCategory>()

    lateinit var itemsRecView: ItemsRecView
    val risItems = ArrayList<RISItem>()

    lateinit var binding:RisScreenMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        risCategories.add(RISCategory(0,"الكل",null,R.drawable.ris_cat_all_icon))
        risCategories.add(RISCategory(1,"مشروبات ساخنة",null, R.drawable.ris_cat_hotdrink_icon))
        risCategories.add(RISCategory(2,"مشروبات باردة",null, R.drawable.ris_catcold_drink_icon))
        risCategories.add(RISCategory(3,"وجبات إفطار",null, R.drawable.ris_cat_breakfast_icon))
        risCategories.add(RISCategory(4,"حلويات",null, R.drawable.ris_cat_sweets_icon))


        val price1 = ArrayList<RISCost>()
        price1.add(RISCost(0,"سنجل",5))
        price1.add(RISCost(1,"دبل",9))
        risItems.add(RISItem(0,"قهوة ريستريتو الإيطالية",price1,"",risCategories[1],false,null))
        val price2 = ArrayList<RISCost>()
        price2.add(RISCost(0,"سنجل",4))
        price2.add(RISCost(1,"دبل",6))
        risItems.add(RISItem(1,"قهوة سبريسّو",price2,"",risCategories[1],false,null))
        val price3 = ArrayList<RISCost>()
        price3.add(RISCost(0,"سنجل",5))
        price3.add(RISCost(1,"دبل",7))
        risItems.add(RISItem(2,"قهوة ماكياتو",price3,"",risCategories[1],false,null))
        val price4 = ArrayList<RISCost>()
        price4.add(RISCost(0,"سنجل",6))
        price4.add(RISCost(1,"دبل",8))
        risItems.add(RISItem(3,"قهوة سبريسّو كونبانا",price4,"",risCategories[1],false,null))
        val price5 = ArrayList<RISCost>()
        price5.add(RISCost(0,"",12))
        risItems.add(RISItem(4,"قهوة موكا بوت (خاصة)",price5,"",risCategories[1],false,null))
        val price6 = ArrayList<RISCost>()
        price6.add(RISCost(0,"سنجل",10))
        price6.add(RISCost(1,"دبل",10))
        risItems.add(RISItem(5,"قهوة فلتر (خاصة)",price6,"",risCategories[1],false,null))
        val price7 = ArrayList<RISCost>()
        price7.add(RISCost(0,"سنجل",7))
        price7.add(RISCost(1,"دبل",7))
        risItems.add(RISItem(6,"قهوة كورتادو",price7,"",risCategories[1],false,null))



        categoriesRecView = CategoriesRecView(risCategories,this)
        binding.CategoryRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.CategoryRecycler.adapter = categoriesRecView

        itemsRecView = ItemsRecView(risItems,this)
        binding.ItemsRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.ItemsRecycler.adapter = itemsRecView



    }
}