package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISReadyOrderShort(

    var OrderId:String,
    var OrderTime:String,
    var OrderDate:String,
    var OrderTable:String?,
    var OrderDetails:Int? = 0


):Serializable
