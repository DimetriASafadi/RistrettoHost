package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISHostNotification(

    var NotId:Long? = 0,
    var NotOrderId:String? = "",
    var NotOrderStatus:Int? = 0,
    var NotTable:String? = "",
    var NotTitle:String? = "",
    var NotDescription:String? = "",
    var NotDeviceId:String? = "",
    var NotTime:String? = "",
    var NotDate:String? = ""

):Serializable
