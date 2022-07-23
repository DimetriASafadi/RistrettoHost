package com.dimetris.ristrettohost.Models

data class RISItem(

    var ItemId:Int? = null,
    var ItemName:String? = "",
    var ItemPrice:ArrayList<RISCost> = ArrayList(),
    var ItemDescription:String? = "",
    var ItemTypeName:String? = "",
    var ItemIsAdditionalCost:Boolean = false,
    var ItemAdditionalCost:RISAdditionalCost? = null

)