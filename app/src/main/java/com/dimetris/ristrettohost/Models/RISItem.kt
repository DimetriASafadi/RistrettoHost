package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISItem(
    var ItemId:Int? = null,
    var ItemName:String? = "",
    var ItemPrice:ArrayList<RISCost> = ArrayList(),
    var ItemDescription:String? = "",
    var ItemCategory:RISCategory? = null,
    var ItemIsAdditionalCost:Boolean = false,
    var ItemAdditionalCost:RISAdditionalCost? = null

):Serializable