package com.serhankhan.legocatalog.ui

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.serhankhan.legocatalog.api.ResultResponse
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.legotheme.ui.LegoThemeViewModel
import com.serhankhan.legocatalog.util.mock
import com.serhankhan.legocatalog.vo.Resource
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import timber.log.Timber

@RunWith(JUnit4::class)
class LegoThemeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(LegoThemeRepository::class.java)
    private var legoThemeViewModel = LegoThemeViewModel(repository)


//    @Test
//    fun testNull(){
//        try {
//            val observer = mock<Observer<Resource<ResultResponse<LegoTheme>>>>()
//            legoThemeViewModel.themes.observeForever(observer)
//            verify(repository).getThemes()
//        }catch (ex:Exception){
//            Timber.d(ex.localizedMessage)
//        }
//
//    }


    @Test
    fun testFetchFromNetworkCalled(){
        legoThemeViewModel.themes
        verify(repository).getThemes()
    }

}