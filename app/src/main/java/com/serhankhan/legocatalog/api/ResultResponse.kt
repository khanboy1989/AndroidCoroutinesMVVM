package com.serhankhan.legocatalog.api

import com.google.gson.annotations.SerializedName

data class ResultResponse<T> (
    @SerializedName("count")
    val count:Int,
    @SerializedName("next")
    val next:String? = null,
    @SerializedName("previous")
    val previous:String? = null,
    @SerializedName("results")
    val results:List<T>)