package com.serhankhan.legocatalog.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.util.ApiUtil.successCall
import com.serhankhan.legocatalog.util.InstantAppExecutors
import com.serhankhan.legocatalog.util.TestContextProvider
import com.serhankhan.legocatalog.util.mock
import com.serhankhan.legocatalog.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response

@RunWith(JUnit4::class)
class LegoThemeRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    //service
    private val service =  mock(LegoService::class.java)
    private lateinit var repository:LegoThemeRepository
    private val appExecutors = InstantAppExecutors()

    @Before
    fun setUp(){
        repository = LegoThemeRepository(TestContextProvider(),appExecutors,service)
    }


    @Test
    fun getThemesSuccess(){

        val observer = mock<Observer<Resource<ResultResponse<LegoTheme>>>>()

        val list:List<LegoTheme> = mutableListOf(LegoTheme(1,"Mario",3))
        val successResponse = ResultResponse(555,null,null,list)
        val call = successCall(successResponse)

        Mockito.`when`(service.getThemes()).thenReturn(call)

        repository.getThemes().observeForever(observer)

        verify(observer).onChanged(Resource.success(ResultResponse(555,null,null,list)))

    }
}