package com.batynchuk.ewave.common.network

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Rest(
    baseUrl: String,
    loggingInterceptor: Interceptor,
    private val gson: Gson
) {
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private val retrofit: Retrofit = getRetrofit(okHttpClient, baseUrl)

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    }
}