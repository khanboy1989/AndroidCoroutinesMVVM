package com.serhankhan.legocatalog.util

import android.content.res.Resources
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeResponse

object TestUtil {

    fun createThemesResponse():LegoThemeResponse = LegoThemeResponse(555,null,null, createThemes())

    fun createThemes():List<LegoTheme>  {
        val mutableList = mutableListOf<LegoTheme>()
        mutableList.add(LegoTheme(1,"Mario",1))
        mutableList.add(LegoTheme(2,"Traffic",2))
        mutableList.add(LegoTheme(3,"Spaces",3))
        return mutableList
    }
}