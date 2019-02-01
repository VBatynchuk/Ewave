package com.batynchuk.ewave.screens.main.di

import com.batynchuk.ewave.di.FragmentScope
import com.batynchuk.ewave.screens.countries.CountriesFragment
import com.batynchuk.ewave.screens.countries.di.CountriesFragmentModule
import com.batynchuk.ewave.screens.neighbours.NeighboursFragment
import com.batynchuk.ewave.screens.neighbours.di.NeighboursFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [CountriesFragmentModule::class])
    fun contributeCountriesFragment(): CountriesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [NeighboursFragmentModule::class])
    fun contributeNeighboursFragment(): NeighboursFragment


}