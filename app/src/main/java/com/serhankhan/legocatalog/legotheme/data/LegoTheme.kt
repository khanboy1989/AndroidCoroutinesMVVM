package com.serhankhan.legocatalog.legotheme.data

import com.google.gson.annotations.SerializedName

data class LegoTheme(
    @field:SerializedName("id")
    val id:Int,
    @field:SerializedName("name")
    val name:String,
    @field:SerializedName("parent_id")
    val parentId:Int? = null
){
    override fun toString(): String = name
}