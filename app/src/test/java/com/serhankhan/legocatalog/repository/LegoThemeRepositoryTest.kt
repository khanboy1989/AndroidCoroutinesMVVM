package com.serhankhan.legocatalog.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.serhankhan.legocatalog.AppExecutors
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.util.InstantAppExecutors
import com.serhankhan.legocatalog.util.TestContextProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

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
    fun getThemes(){
        repository.getThemes()
        verify(service).getThemes()
    }
}