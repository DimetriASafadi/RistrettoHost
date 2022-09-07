package com.dimetris.ristrettohost.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RISReadyOrder(

    var OrderId:String,
    var OrderTime:String,
    var OrderDate:String,
    var OrderTable:String?,
    var OrderDeviceId:String? = "",
    var OrderDetails:ArrayList<RISCartItem>

):Serializable