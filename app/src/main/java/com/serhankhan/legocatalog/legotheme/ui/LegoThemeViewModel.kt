package com.serhankhan.legocatalog.legotheme.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.testing.OpenForTesting
import com.serhankhan.legocatalog.vo.Resource
import javax.inject.Inject

@OpenForTesting
class LegoThemeViewModel @Inject constructor(private val repository:LegoThemeRepository):ViewModel() {

    private val _themeRequestParams = MutableLiveData<ThemeRequestParams>()

    val themeRequestParams:LiveData<ThemeRequestParams>
    get() = _themeRequestParams

    //Themes live data observed by LegoThemeFragment
    var themes:LiveData<Resource<List<LegoTheme>>> = _themeRequestParams.switchMap {
        params-> repository.getThemes(params.page,params.page_size,params.order)
    }

    fun setThemeRequestParams(page:Int?=null,page_size:Int?=null,order:String? = null) {
        val update = ThemeRequestParams(page,page_size,order)
        if (_themeRequestParams.value == update) {
            return
        }
        _themeRequestParams.value = update
    }

    data class ThemeRequestParams (
        var page:Int? = null,
        var page_size:Int? = null,
        var order:String? = null)
}

