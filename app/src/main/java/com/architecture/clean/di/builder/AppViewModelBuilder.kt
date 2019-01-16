package com.architecture.clean.di.builder

import androidx.lifecycle.ViewModel
import com.architecture.clean.di.qualifier.ViewModelKey
import com.architecture.clean.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}