package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISReadyOrder(

    var OrderId:String,
    var OrderTime:String,
    var OrderDate:String,
    var OrderTable:String?,
    var OrderDetails:ArrayList<RISCartItem>

):Serializable