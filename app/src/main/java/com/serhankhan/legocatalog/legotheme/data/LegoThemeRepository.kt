package com.serhankhan.legocatalog.legotheme.data

import androidx.lifecycle.LiveData
import com.serhankhan.legocatalog.AppExecutors
import com.serhankhan.legocatalog.Repository.NetworkBoundResource
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.testing.OpenForTesting
import com.serhankhan.legocatalog.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class LegoThemeRepository @Inject constructor(private val appExecutors: AppExecutors, private val service:LegoService) {

    fun getThemes():LiveData<Resource<ResultResponse<LegoTheme>>>{
        return object : NetworkBoundResource<ResultResponse<LegoTheme>>(appExecutors) {
            override fun createCall() = service.getThemes()
            override fun onFetchFailed() {}
        }.asLiveData()
    }

}