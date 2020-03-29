package com.serhankhan.legocatalog.di

import androidx.lifecycle.ViewModel
import com.serhankhan.legocatalog.legotheme.ui.LegoThemeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LegoThemeViewModel::class)
    abstract fun bindLegoThemeViewModel(legoThemeViewModel:LegoThemeViewModel):ViewModel


}