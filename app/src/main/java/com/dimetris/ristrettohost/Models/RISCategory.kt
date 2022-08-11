package com.dimetris.ristrettohost.Models

import java.io.Serializable

data class RISCategory(
    var CatId:Int? = null,
    var CatName:String? = "",
    var CatColor:Int? = null,
    var CatIcon:Int? = null
): Serializable
