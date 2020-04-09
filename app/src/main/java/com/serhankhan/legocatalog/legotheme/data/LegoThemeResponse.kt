package com.serhankhan.legocatalog.legotheme.data

import com.google.gson.annotations.SerializedName

data class LegoThemeResponse (
    @SerializedName("count")
    val count:Int,
    @SerializedName("next")
    val next:String? = null,
    @SerializedName("previous")
    val previous:String? = null,
    @SerializedName("results")
    var results:List<LegoTheme>?
)