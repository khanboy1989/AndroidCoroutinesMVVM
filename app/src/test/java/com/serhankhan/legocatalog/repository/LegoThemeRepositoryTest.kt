package com.serhankhan.legocatalog.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.data.AppDatabase
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeDao
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.util.ApiUtil.successCall
import com.serhankhan.legocatalog.util.InstantAppExecutors
import com.serhankhan.legocatalog.util.TestContextProvider
import com.serhankhan.legocatalog.util.TestUtil
import com.serhankhan.legocatalog.util.mock
import com.serhankhan.legocatalog.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LegoThemeRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    //service
    private val service =  mock(LegoService::class.java)
    private lateinit var repository:LegoThemeRepository
    private val appExecutors = InstantAppExecutors()

    @Mock
    private lateinit var dao:LegoThemeDao

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        val db = mock(AppDatabase::class.java)
        `when`(db.legoThemeDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = LegoThemeRepository(TestContextProvider(),appExecutors,service,dao,db)
    }

    @Test
    fun getThemes(){
        val dbData = MutableLiveData<List<LegoTheme>>()
        `when`(dao.getLegoThemes()).thenReturn(dbData)

        val themes = TestUtil.createThemesResponse()
        val call = successCall(themes)
        `when`(service.getThemes()).thenReturn(call)

        val data = repository.getThemes()
        //verify(dao).getLegoThemes()
        verifyNoMoreInteractions(service)


    }



}