package com.serhankhan.legocatalog.legotheme.data

import androidx.lifecycle.LiveData
import com.serhankhan.legocatalog.AppExecutors
import com.serhankhan.legocatalog.ContextProviders
import com.serhankhan.legocatalog.api.ApiResponse
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.data.AppDatabase
import com.serhankhan.legocatalog.repository.NetworkResourceBound
import com.serhankhan.legocatalog.testing.OpenForTesting
import com.serhankhan.legocatalog.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class LegoThemeRepository @Inject constructor(private val contextProviders: ContextProviders,private val appExecutors: AppExecutors, private val service:LegoService,private val legoThemeDao: LegoThemeDao,private val db:AppDatabase) {

    fun getThemes():LiveData<Resource<List<LegoTheme>>> {
        return object :NetworkResourceBound<List<LegoTheme>, LegoThemeResponse>(contextProviders){
            override fun saveCallResult(item: LegoThemeResponse) {
                val themes = item.results
                db.runInTransaction{
                    themes?.let {
                        legoThemeDao.deleteAllThemes()
                        legoThemeDao.insertAll(it)
                    }
                }
            }

            override fun shouldFetch(data: List<LegoTheme>?): Boolean = true

            override fun loadFromDb(): LiveData<List<LegoTheme>>  = legoThemeDao.getLegoThemes()

            override fun createCall(): LiveData<ApiResponse<LegoThemeResponse>> = service.getThemes()

        }.asLiveData()
    }
}