package com.serhankhan.legocatalog.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.serhankhan.legocatalog.legotheme.data.LegoTheme
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.legotheme.data.LegoThemeResponse
import com.serhankhan.legocatalog.legotheme.ui.LegoThemeViewModel
import com.serhankhan.legocatalog.vo.Resource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.serhankhan.legocatalog.util.mock
import org.junit.Before
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LegoThemeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(LegoThemeRepository::class.java)
    private var legoThemeViewModel = LegoThemeViewModel(repository)

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun doNotFetchWithoutObserver(){
        legoThemeViewModel.setThemeRequestParams(1)
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun fetchWhenObserved(){
        legoThemeViewModel.setThemeRequestParams(1)
        legoThemeViewModel.themes.observeForever(mock())
        verify(repository).getThemes(1)
    }

}