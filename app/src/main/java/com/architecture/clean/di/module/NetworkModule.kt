package com.architecture.clean.di.module

import com.architecture.clean.core.Config
import com.architecture.clean.data.restful.ApiService
import com.architecture.clean.data.source.cloud.BaseCloudRepository
import com.architecture.clean.data.source.cloud.CloudRepository
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Config.HOST)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
                //  .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(interceptor)
        return client.build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }



    @Singleton
    @Provides
    fun provideService( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    fun provideCloudRepository(apIs: ApiService): BaseCloudRepository {
        return CloudRepository(apIs)
    }


}