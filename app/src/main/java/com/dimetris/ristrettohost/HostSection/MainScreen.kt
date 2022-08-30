package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimetris.ristrettohost.CommonsSection.CommonFuncs
import com.dimetris.ristrettohost.CommonsSection.Constants.cartItems
import com.dimetris.ristrettohost.InterFaces.OnCategoryClick
import com.dimetris.ristrettohost.Models.RISAdditionalCost
import com.dimetris.ristrettohost.Models.RISCategory
import com.dimetris.ristrettohost.Models.RISCost
import com.dimetris.ristrettohost.Models.RISItem
import com.dimetris.ristrettohost.R
import com.dimetris.ristrettohost.RecViews.CategoriesRecView
import com.dimetris.ristrettohost.RecViews.ItemsRecView
import com.dimetris.ristrettohost.databinding.RisScreenMainBinding

class MainScreen : AppCompatActivity(), OnCategoryClick {

    val commonFuncs = CommonFuncs()

    lateinit var categoriesRecView: CategoriesRecView
    val risCategories = ArrayList<RISCategory>()

    lateinit var itemsRecView: ItemsRecView
    val risItems = ArrayList<RISItem>()


    lateinit var binding:RisScreenMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonFuncs.LanguageCheck(this)
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
        risItems.add(RISItem(1,"قهوة سبريسو",price2,"",risCategories[1],false,null))
        val price3 = ArrayList<RISCost>()
        price3.add(RISCost(0,"سنجل",5))
        price3.add(RISCost(1,"دبل",7))
        risItems.add(RISItem(2,"قهوة ماكياتو",price3,"",risCategories[1],false,null))
        val price4 = ArrayList<RISCost>()
        price4.add(RISCost(0,"سنجل",6))
        price4.add(RISCost(1,"دبل",8))
        risItems.add(RISItem(3,"قهوة سبريسو كونبانا",price4,"",risCategories[1],false,null))
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
        risItems.add(RISItem(27,"مياه معدنية",price28,"",risCategories[2],false,null))
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
        val price35 = ArrayList<RISCost>()
        price35.add(RISCost(0,"",9))
        price35.add(RISCost(1,"",12))
        risItems.add(RISItem(34,"كابوتشينو مثلج",price35,"متوفر بالنكهات (البندق,كراميل,الفانيلا)",risCategories[2],false,null))
        val price36 = ArrayList<RISCost>()
        price36.add(RISCost(0,"",9))
        price36.add(RISCost(1,"",12))
        risItems.add(RISItem(35,"لاتيه مثلج",price36,"متوفر بالنكهات (البندق,كراميل,الفانيلا)",risCategories[2],false,null))
        val price37 = ArrayList<RISCost>()
        price37.add(RISCost(0,"",8))
        price37.add(RISCost(1,"",12))
        risItems.add(RISItem(36,"أمريكانو مثلج",price37,"يقدم ساخنا أو مع الحليب",risCategories[2],false,null))
        val price38 = ArrayList<RISCost>()
        price38.add(RISCost(0,"",8))
        price38.add(RISCost(1,"",12))
        risItems.add(RISItem(37,"آيس موكا",price38,"متوفر بالنكهات (البندق,كراميل,الفانيلا)",risCategories[2],false,null))
        val price39 = ArrayList<RISCost>()
        price39.add(RISCost(0,"",8))
        price39.add(RISCost(1,"",10))
        risItems.add(RISItem(38,"ميلك شيك",price39,"شيء فاخر لمذاقكم بطعم (الفراولة,نوتيلا,أوريو,فانيلا)",risCategories[2],false,null))
        val price40 = ArrayList<RISCost>()
        price40.add(RISCost(0,"",12))
        risItems.add(RISItem(39,"ميلك شيك بستاشبو",price40,"متوفر بالنكهات الأصليّة",risCategories[2],false,null))
        val price41 = ArrayList<RISCost>()
        price41.add(RISCost(0,"",8))
        price41.add(RISCost(1,"",12))
        risItems.add(RISItem(40,"الفرباتشينو",price41,"مشروب فريد من نوعه, نقدّمه بنكهات متعددة (الكراميل, الفراولة, الشوكولاتة, النوتيلا, الاسبريسو, الموكا, والفانيلا), والذي يضاف إليه الحليب والكريمة",risCategories[2],false,null))


        val price42 = ArrayList<RISCost>()
        price42.add(RISCost(0,"شخص واحد",10))
        risItems.add(RISItem(41,"بوكس افطار ريستريتو (شخص)",price42,"يتكون الصندوق من عدد 5 قطع ساليزون مشكّل (جبنة بيضاء, جبنة صفراء, لانشون, مرتديلا, صدر دجاج) مضافاً إليها الخضار حسب القطعة, بالإضافة إلى صوصّات ريستريتو, وقطعة تارت للتحلية",risCategories[3],false,null))
        val price43 = ArrayList<RISCost>()
        price43.add(RISCost(0,"شخصين",17))
        risItems.add(RISItem(42,"بوكس افطار ريستريتو (شخصين)",price43,"يتكون الصندوق من عدد 10 قطع ساليزون مشكّل (جبنة بيضاء, جبنة صفراء, لانشون, مرتديلا, صدر دجاج) مضافاً إليها الخضار حسب القطعة, بالإضافة إلى صوصّات ريستريتو, وقطعتين تارت للتحلية",risCategories[3],false,null))
        val price44 = ArrayList<RISCost>()
        price44.add(RISCost(0,"",4))
        risItems.add(RISItem(43,"توست",price44,"حسب الأطعمة المتوفرة يومياً (جبنة صفراء,بيضاء,لانشون)",risCategories[3],false,null))
        val price45 = ArrayList<RISCost>()
        price45.add(RISCost(0,"",6))
        risItems.add(RISItem(44,"سلامي",price45,"حسب الأطعمة المتوفرة يومياً (جبنة صفراء,بيضاء,لانشون)",risCategories[3],false,null))
        val price46 = ArrayList<RISCost>()
        price46.add(RISCost(0,"",3))
        risItems.add(RISItem(45,"معجنات اللحم وعجين",price46,"حجم صغير (ميني)",risCategories[3],false,null))
        val price47 = ArrayList<RISCost>()
        price47.add(RISCost(0,"",5))
        risItems.add(RISItem(46,"سلطة ريستريتو اليومية",price47,"تصنع يومياً وخصيصاً, وتتكون من قطع الخضروات بحجم وسط, مع الجبنة البلدية البيضاء",risCategories[3],false,null))
        val price48 = ArrayList<RISCost>()
        price48.add(RISCost(0,"",4))
        risItems.add(RISItem(47,"باجيت جبنة بلدية",price48,"حسب الأطعمة المتوفرة يومياً, ويقدم من الثلاجة فورا",risCategories[3],false,null))
        val price49 = ArrayList<RISCost>()
        price49.add(RISCost(0,"",2))
        risItems.add(RISItem(48,"المعجنات صغيرة",price49,"حسب المتوفر يوميا (البيتزا, نقانق, الجبنة, الزعتر)",risCategories[3],false,null))
        val price50 = ArrayList<RISCost>()
        price50.add(RISCost(0,"",5))
        risItems.add(RISItem(49,"سلطة سيزر",price50,"تصنع يومياً وخصيصاً, وتتكون من قطع الخضروات بحجم وسط, مع قطع الخبز المحمص",risCategories[3],false,null))

        val price51 = ArrayList<RISCost>()
        price51.add(RISCost(0,"(الحجم العادي)",10))
        val adprice2 = RISAdditionalCost(0,"2 شيكل إضافية عند إضافة البوظة على الوافل",2)
        risItems.add(RISItem(50,"حلوى الوافل",price51,"متوفر بطعم (نوتيلا, لوتس, بستاشيو) ويضاف إليها المكسرات",risCategories[4],false,adprice2))
        val price52 = ArrayList<RISCost>()
        price52.add(RISCost(0,"(الحجم العادي)",15))
        risItems.add(RISItem(51,"حلوى الوافل ميكس",price52,"متوفر بطعم (نوتيلا, لوتس, بستاشيو) ويضاف إليها المكسرات",risCategories[4],false,null))
        val price53 = ArrayList<RISCost>()
        price53.add(RISCost(0,"(الحجم العادي)",12))
        val adprice3 = RISAdditionalCost(0,"2 شيكل إضافية عند إضافة البوظة على الكريب",2)
        risItems.add(RISItem(52,"حلوى الكريب",price53,"متوفر بطعم (نوتيلا, لوتس, بستاشيو) ويضاف إليها المكسرات",risCategories[4],false,adprice3))
        val price54 = ArrayList<RISCost>()
        price54.add(RISCost(0,"(الحجم العادي)",10))
        val adprice4 = RISAdditionalCost(0,"2 شيكل إضافية عند إضافة البوظة على اللقيمات",2)
        risItems.add(RISItem(53,"لقيمات",price54,"متوفر بطعم (نوتيلا, لوتس, بستاشيو) ويضاف إليها المكسرات",risCategories[4],false,adprice4))
        val price55 = ArrayList<RISCost>()
        price55.add(RISCost(0,"(الحجم العادي)",10))
        val adprice5 = RISAdditionalCost(0,"2 شيكل إضافية عند إضافة البوظة على البان كيك",2)
        risItems.add(RISItem(54,"بان كيك",price55,"متوفر بطعم (نوتيلا, لوتس, بستاشيو) ويضاف إليها المكسرات",risCategories[4],false,adprice5))
        val price56 = ArrayList<RISCost>()
        price56.add(RISCost(0,"(الحجم العادي)",5))
        risItems.add(RISItem(55,"كرواسون",price56,"حسب الأطعمة المتوفرة يومياً",risCategories[4],false,null))
        val price57 = ArrayList<RISCost>()
        price57.add(RISCost(0,"(الحجم العادي)",5))
        risItems.add(RISItem(56,"كيك",price57,"متوفر بطعم (الشوكولاتة, الكراميل)",risCategories[4],false,null))
        val price58 = ArrayList<RISCost>()
        price58.add(RISCost(0,"(الحجم العادي)",15))
        risItems.add(RISItem(57,"سيزلنغ ريستريتو",price58,"محيط من الشوكولاتة الساخنة والبراونيز الداكنة مغطاة بالآيس كريم المثالي",risCategories[4],false,null))
        val price59 = ArrayList<RISCost>()
        price59.add(RISCost(0,"(الحجم العادي)",12))
        risItems.add(RISItem(58,"ذا قود فاذر",price59,"الآيسكريم بنكهات الفانيليا او الشوكولاتة مع عناصر ساخنة من كيكة الماربل والموفينز والبراونيز ممزوجة بالمكسرات وقطع الشوكولاتة",risCategories[4],false,null))
        val price60 = ArrayList<RISCost>()
        price60.add(RISCost(0,"",4))
        risItems.add(RISItem(59,"كوكيز/ كُب كيك",price60,"قطع مخصصة من البسكويت والكيك بالشوكولاتة الأمريكية الفاخرة",risCategories[4],false,null))

        categoriesRecView = CategoriesRecView(risCategories,this,this)
        binding.CategoryRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.CategoryRecycler.adapter = categoriesRecView

        itemsRecView = ItemsRecView(risItems,this)
        binding.ItemsRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.ItemsRecycler.adapter = itemsRecView


        binding.HEDSearch.addTextChangedListener {
            itemsRecView.nameQuery = binding.HEDSearch.text.toString()
            itemsRecView.notifyDataSetChanged()
        }


        binding.ItemsCart.setOnClickListener {
            commonFuncs.OpenCartDialog(this,binding.ItemsCartCount)
        }


    }

    override fun onResume() {
        super.onResume()
        commonFuncs.LanguageCheck(this)
        commonFuncs.GetCart(this)
        commonFuncs.CheckCart(binding.ItemsCartCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        commonFuncs.hideLoadingDialog()
    }



    override fun OnCategoryClickListener(catid: Int) {
        itemsRecView.selectedCategory = catid
        itemsRecView.notifyDataSetChanged()
    }
}