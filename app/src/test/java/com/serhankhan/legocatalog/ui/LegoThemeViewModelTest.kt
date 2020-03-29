package com.serhankhan.legocatalog.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.serhankhan.legocatalog.legotheme.data.LegoThemeRepository
import com.serhankhan.legocatalog.legotheme.ui.LegoThemeViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class LegoThemeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(LegoThemeRepository::class.java)
    private var legoThemeViewModel = LegoThemeViewModel(repository)


    @Test
    fun testNull(){
        assertThat(legoThemeViewModel.themes, nullValue())
        verify(repository).getThemes()
    }


    @Test
    fun testFetchFromNetworkCalled(){
        legoThemeViewModel.themes
        verify(repository).getThemes()
    }

}