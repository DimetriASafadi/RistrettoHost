package com.dimetris.ristrettohost.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RISReadyOrderShort(


    @SerializedName("OrderId")
    var OrderId:String,
    @SerializedName("OrderTime")
    var OrderTime:String,
    @SerializedName("OrderDate")
    var OrderDate:String,
    @SerializedName("OrderTable")
    var OrderTable:String?,
    @SerializedName("OrderStatus")
    var OrderStatus:Int? = 0,
    @SerializedName("OrderTotal")
    var OrderTotal:Int? = 0


):Serializable
