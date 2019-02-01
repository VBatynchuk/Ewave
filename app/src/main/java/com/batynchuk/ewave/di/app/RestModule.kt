package com.batynchuk.ewave.di.app

import android.content.Context
import com.batynchuk.ewave.R
import com.batynchuk.ewave.common.network.Rest
import com.batynchuk.ewave.data.country.CountriesApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun provideRest(
        baseApiUrl: String,
        loggingInterceptor: Interceptor,
        gson: Gson
    ): Rest {
        return Rest(
            baseApiUrl,
            loggingInterceptor,
            gson
        ) // TODO change base
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

//    @Provides
//    @Singleton
//    @AuthenticationInterceptor
//    fun provideAuthenticationInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val newUrl = chain.request().url()
//                .newBuilder()
//                .build()
//
//            val newRequest = chain.request()
//                .newBuilder()
//                .url(newUrl)
//                .build()
//
//            chain.proceed(newRequest)
//        }
//    }

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideDailyApi(rest: Rest): CountriesApi {
        return rest.create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(@ApplicationContext context: Context):  String{
        return context.getString(R.string.base_api_url)
    }

}


