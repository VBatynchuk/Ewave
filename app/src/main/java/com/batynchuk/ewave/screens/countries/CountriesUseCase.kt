package com.batynchuk.ewave.screens.countries

import com.batynchuk.ewave.data.country.CountriesRepository
import com.batynchuk.ewave.data.country.CountryDto
import kotlinx.coroutines.Deferred
import javax.inject.Inject

interface CountriesListUseCase {
    fun getAllCountries(): Deferred<List<CountryDto>>
}

class CountriesListFragmentUseCase @Inject constructor(private val countriesRepository: CountriesRepository) : CountriesListUseCase {
    override fun getAllCountries(): Deferred<List<CountryDto>> = countriesRepository.getAllCountries()
}