package com.architecture.clean.di.component

import android.app.Application
import com.architecture.clean.core.App
import com.architecture.clean.di.builder.ActivityBuilder
import com.architecture.clean.di.module.ContextModule
import com.architecture.clean.di.module.DataBaseModule
import com.architecture.clean.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class,  ActivityBuilder::class, ContextModule::class,DataBaseModule::class])
interface CoreComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }


}