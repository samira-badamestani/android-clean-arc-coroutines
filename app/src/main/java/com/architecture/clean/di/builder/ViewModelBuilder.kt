package com.architecture.clean.di.builder

import androidx.lifecycle.ViewModelProvider
import com.architecture.clean.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [
    (RepositoryBuilder::class),
    (AppViewModelBuilder::class)
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}