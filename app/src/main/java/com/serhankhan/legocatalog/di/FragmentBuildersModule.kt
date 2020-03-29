package com.serhankhan.legocatalog.di

import com.serhankhan.legocatalog.legotheme.ui.LegoThemeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeThemeFragment(): LegoThemeFragment

}