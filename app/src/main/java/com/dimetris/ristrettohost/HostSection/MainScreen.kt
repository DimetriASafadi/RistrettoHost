package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.Models.RISAdditionalCost
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
        val price8 = ArrayList<RISCost>()
        price8.add(RISCost(0,"",7))
        price8.add(RISCost(1,"",10))
        risItems.add(RISItem(7,"كابوتشينو",price8,"متوفر بالنكهات (البندق,كراميل,الفانيلا)",risCategories[1],false,null))
        val price9 = ArrayList<RISCost>()
        price9.add(RISCost(0,"",7))
        price9.add(RISCost(1,"",10))
        risItems.add(RISItem(8,"كافيه لاتيه",price9,"متوفر بالنكهات (البندق,كراميل,الفانيلا)",risCategories[1],false,null))
        val price10 = ArrayList<RISCost>()
        price10.add(RISCost(0,"",7))
        risItems.add(RISItem(9,"أمريكانو",price10,"يقدم ساخناً أو مع الحليب",risCategories[1],false,null))
        val price11 = ArrayList<RISCost>()
        price11.add(RISCost(0,"",7))
        price11.add(RISCost(1,"",10))
        risItems.add(RISItem(10,"كافيه موكا",price11,"الشوكولاتة الساخنة المحبَّبة",risCategories[1],false,null))
        val price12 = ArrayList<RISCost>()
        price12.add(RISCost(0,"",7))
        price12.add(RISCost(1,"",9))
        risItems.add(RISItem(11,"فلات وايت",price12,"قهوة فاخرة لمذاقكم",risCategories[1],false,null))
        val price13 = ArrayList<RISCost>()
        price13.add(RISCost(0,"",7))
        price13.add(RISCost(1,"",11))
        risItems.add(RISItem(12,"كافيه نوتيلا",price13,"متوفر بنكهة النوتيلا الأصليّة",risCategories[1],false,null))
        val price14 = ArrayList<RISCost>()
        price14.add(RISCost(0,"",7))
        price14.add(RISCost(1,"",10))
        risItems.add(RISItem(13,"شوكولاتة ساخنة",price14,"يقدم خليطاً من الشوكولاتة مع الحليب السّاخن",risCategories[1],false,null))
        val price15 = ArrayList<RISCost>()
        price15.add(RISCost(0,"",7))
        price15.add(RISCost(1,"",10))
        risItems.add(RISItem(14,"سبانيش لاتيه",price15,"يقدم بالنكهات المختلفة",risCategories[1],false,null))
        val price16 = ArrayList<RISCost>()
        price16.add(RISCost(0,"",3))
        price16.add(RISCost(1,"",5))
        risItems.add(RISItem(15,"قهوة تركية",price16,"",risCategories[1],false,null))
        val price17 = ArrayList<RISCost>()
        price17.add(RISCost(0,"",3))
        risItems.add(RISItem(16,"شاي",price17,"",risCategories[1],false,null))
        val price18 = ArrayList<RISCost>()
        price18.add(RISCost(0,"",5))
        price18.add(RISCost(1,"",7))
        risItems.add(RISItem(17,"نسكافيه",price18,"",risCategories[1],false,null))
        val price19 = ArrayList<RISCost>()
        price19.add(RISCost(0,"",3))
        risItems.add(RISItem(18,"مشروب البابونج",price19,"",risCategories[1],false,null))
        val price20 = ArrayList<RISCost>()
        price20.add(RISCost(0,"",3))
        price20.add(RISCost(1,"",5))
        risItems.add(RISItem(19,"أعشاب طبيعية",price20,"",risCategories[1],false,null))
        val price21 = ArrayList<RISCost>()
        price21.add(RISCost(0,"",5))
        risItems.add(RISItem(20,"مشروب النعنع",price21,"",risCategories[1],false,null))


        val price22 = ArrayList<RISCost>()
        price22.add(RISCost(0,"",12))
        price22.add(RISCost(1,"",15))
        risItems.add(RISItem(21,"موهيتو إكس إل XL",price22,"",risCategories[2],false,null))
        val price23 = ArrayList<RISCost>()
        price23.add(RISCost(0,"",10))
        price23.add(RISCost(1,"",12))
        risItems.add(RISItem(22,"موهيتو بأنواعه",price23,"(الفواكه الموسمية تضاف للمشروب)",risCategories[2],false,null))
        val price24 = ArrayList<RISCost>()
        price24.add(RISCost(0,"",8))
        price24.add(RISCost(1,"",10))
        risItems.add(RISItem(23,"بلو & ريد",price24,"",risCategories[2],false,null))
        val price25 = ArrayList<RISCost>()
        price25.add(RISCost(0,"",4))
        risItems.add(RISItem(24,"بوكا قهوة",price25,"(مثلجة جاهزة)",risCategories[2],false,null))
        val price26 = ArrayList<RISCost>()
        price26.add(RISCost(0,"",8))
        price26.add(RISCost(1,"",10))
        risItems.add(RISItem(25,"ليمون و نعنع",price26,"",risCategories[2],false,null))
        val price27 = ArrayList<RISCost>()
        price27.add(RISCost(0,"",3))
        price27.add(RISCost(1,"",5))
        val adprice1 = RISAdditionalCost(0,"(إضافة ثلج, ليمون ونعنع بـ1 شيكل)",1)
        risItems.add(RISItem(26,"مشروب غازي",price27,"(كوكاكولا,بيبسي,شويبس,XL,..)",risCategories[2],false,adprice1))
        val price28 = ArrayList<RISCost>()
        price28.add(RISCost(0,"(500 ملم)",2))
        price28.add(RISCost(1,"(1.5 لتر)",5))
        risItems.add(RISItem(27,"مياه معدنيّة",price28,"",risCategories[2],false,null))
        val price29 = ArrayList<RISCost>()
        price29.add(RISCost(0,"",6))
        price29.add(RISCost(1,"",8))
        risItems.add(RISItem(28,"البرتقال",price29,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))
        val price30 = ArrayList<RISCost>()
        price30.add(RISCost(0,"",8))
        price30.add(RISCost(1,"",10))
        risItems.add(RISItem(29,"المانجو",price30,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))
        val price31 = ArrayList<RISCost>()
        price31.add(RISCost(0,"",8))
        price31.add(RISCost(1,"",10))
        risItems.add(RISItem(30,"الموز",price31,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))
        val price32 = ArrayList<RISCost>()
        price32.add(RISCost(0,"",8))
        price32.add(RISCost(1,"",10))
        risItems.add(RISItem(31,"الفراولة",price32,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))
        val price33 = ArrayList<RISCost>()
        price33.add(RISCost(0,"",8))
        price33.add(RISCost(1,"",10))
        risItems.add(RISItem(32,"الأناناس",price33,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))
        val price34 = ArrayList<RISCost>()
        price34.add(RISCost(0,"",8))
        price34.add(RISCost(1,"",10))
        risItems.add(RISItem(33,"كوكتيل الموسم",price34,"الفواكه المعصورة خصيصاً وطبيعية بنسبة 100%",risCategories[2],false,null))


        categoriesRecView = CategoriesRecView(risCategories,this)
        binding.CategoryRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.CategoryRecycler.adapter = categoriesRecView

        itemsRecView = ItemsRecView(risItems,this)
        binding.ItemsRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.ItemsRecycler.adapter = itemsRecView



    }
}