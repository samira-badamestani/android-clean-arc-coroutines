package com.architecture.clean.di.builder

import com.architecture.clean.data.repository.AppRepoImp
import com.architecture.clean.domain.repository.AppRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryBuilder {
    @Binds
    abstract fun bindsMovieRepository(repoImp: AppRepoImp): AppRepository
}