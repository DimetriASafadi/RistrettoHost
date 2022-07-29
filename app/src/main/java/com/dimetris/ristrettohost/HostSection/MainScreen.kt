package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.Models.RISCategory
import com.dimetris.ristrettohost.R
import com.dimetris.ristrettohost.RecViews.CategoriesRecView
import com.dimetris.ristrettohost.databinding.RisScreenMainBinding

class MainScreen : AppCompatActivity() {

    lateinit var categoriesRecView: CategoriesRecView
    val risCategories = ArrayList<RISCategory>()

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

        categoriesRecView = CategoriesRecView(risCategories,this)
        binding.CategoryRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.CategoryRecycler.adapter = categoriesRecView









    }
}