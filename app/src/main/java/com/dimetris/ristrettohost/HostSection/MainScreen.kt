package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.Models.RISCategory
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

        risCategories.add(RISCategory(0,"الكل"))
        risCategories.add(RISCategory(1,"مشروبات ساخنة"))
        risCategories.add(RISCategory(2,"مشروبات باردة"))
        risCategories.add(RISCategory(3,"وجبات إفطار"))
        risCategories.add(RISCategory(4,"حلويات"))

        categoriesRecView = CategoriesRecView(risCategories,this)
        binding.CategoryRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.CategoryRecycler.adapter = categoriesRecView







    }
}