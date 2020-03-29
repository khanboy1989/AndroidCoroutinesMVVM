package com.serhankhan.legocatalog.legotheme.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.testing.OpenForTesting
import com.serhankhan.legocatalog.vo.Resource
import javax.inject.Inject

@OpenForTesting
class LegoThemeViewModel @Inject constructor(private val repository:LegoThemeRepository):ViewModel() {

    //Themes live data observed by LegoThemeFragment
    val themes:LiveData<Resource<ResultResponse<LegoTheme>>> = repository.getThemes()
}

