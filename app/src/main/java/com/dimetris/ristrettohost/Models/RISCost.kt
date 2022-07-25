package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISCost(

    var CostId:Int? = null,
    var CostName:String? = "",
    var CostValue:Int? = null,
    var CostIsSelected:Boolean = false,


): Serializable
