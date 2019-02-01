package com.batynchuk.ewave.screens.countries.di

import androidx.lifecycle.ViewModelProviders
import com.batynchuk.ewave.di.FragmentScope
import com.batynchuk.ewave.screens.countries.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CountriesFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindCountriesUseCase(countriesListFragmentUseCase: CountriesListFragmentUseCase): CountriesListUseCase

    @Binds
    @FragmentScope
    abstract fun bindCountriesRouter(countriesRouter: CountriesFragmentRouter): CountriesRouter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideCountriesViewModel(
            fragment: CountriesFragment,
            countriesViewModelFactory: CountriesViewModelFactory
        ): CountriesViewModel {
            return ViewModelProviders.of(fragment, countriesViewModelFactory).get(CountriesViewModel::class.java)
        }
    }

}