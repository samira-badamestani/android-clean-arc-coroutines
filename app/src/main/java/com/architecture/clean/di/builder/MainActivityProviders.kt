package com.architecture.clean.di.builder

import com.architecture.clean.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders{
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

}