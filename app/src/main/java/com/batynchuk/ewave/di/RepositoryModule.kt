package com.batynchuk.ewave.di

import com.batynchuk.ewave.data.country.CountriesRemoteRepositoryImpl
import com.batynchuk.ewave.data.country.CountriesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindCountriesRepository(dailyForecastRemoteRepository: CountriesRemoteRepositoryImpl): CountriesRepository

}