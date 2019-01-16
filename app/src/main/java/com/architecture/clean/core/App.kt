package com.architecture.clean.core


import android.content.Context
import androidx.multidex.MultiDex
import com.architecture.clean.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}