package com.dimetris.ristrettohost.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RISReadyOrder(

    @SerializedName("OrderId")
    var OrderId:String,
    @SerializedName("OrderTime")
    var OrderTime:String,
    @SerializedName("OrderDate")
    var OrderDate:String,
    @SerializedName("OrderTable")
    var OrderTable:String?,
    @SerializedName("OrderDeviceId")
    var OrderDeviceId:String? = "",
    var OrderDetails:ArrayList<RISCartItem>

):Serializable