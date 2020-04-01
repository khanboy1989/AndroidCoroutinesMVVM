package com.serhankhan.legocatalog.legotheme.data

import androidx.lifecycle.LiveData
import com.serhankhan.legocatalog.AppExecutors
import com.serhankhan.legocatalog.ContextProviders
import com.serhankhan.legocatalog.api.ApiResponse
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.repository.NetworkResourceBound
import com.serhankhan.legocatalog.testing.OpenForTesting
import com.serhankhan.legocatalog.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class LegoThemeRepository @Inject constructor(private val contextProviders: ContextProviders,private val appExecutors: AppExecutors, private val service:LegoService) {



    fun getThemes():LiveData<Resource<ResultResponse<LegoTheme>>> {
        return object :NetworkResourceBound<ResultResponse<LegoTheme>, ResultResponse<LegoTheme>>(contextProviders){

            override fun createCall(): LiveData<ApiResponse<ResultResponse<LegoTheme>>> = service.getThemes()

            override fun onFetchFailed() {
            }
        }.asLiveData()
    }
}