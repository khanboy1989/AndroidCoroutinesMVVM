package com.serhankhan.legocatalog.di

import com.serhankhan.legocatalog.BuildConfig
import com.serhankhan.legocatalog.ContextProviders
import com.serhankhan.legocatalog.api.AuthInterceptor
import com.serhankhan.legocatalog.api.LegoService
import com.serhankhan.legocatalog.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class,CoreDataModule::class])
class AppModule {


    @Singleton
    @Provides
    fun provideLegoService(@LegoAPI okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory,callAdapter: LiveDataCallAdapterFactory
    ) = provideService(okhttpClient, converterFactory,callAdapter, LegoService::class.java)



    @LegoAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_DEVELOPER_TOKEN)).build()
    }

    @Provides
    fun provideContextProvider() = ContextProviders()

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,callAdapter:LiveDataCallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LegoService.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory,callAdapter: LiveDataCallAdapterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory,callAdapter).create(clazz)
    }
}