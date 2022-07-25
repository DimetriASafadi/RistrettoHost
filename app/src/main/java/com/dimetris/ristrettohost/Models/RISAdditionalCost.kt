package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISAdditionalCost(
    var AddCostId:Int? = null,
    var AddCostDesc:String? = null,
    var AddCostValue:Int? = null,
): Serializable
