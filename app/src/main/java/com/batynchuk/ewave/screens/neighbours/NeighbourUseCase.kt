package com.batynchuk.ewave.screens.neighbours

import com.batynchuk.ewave.data.country.CountriesRepository
import com.batynchuk.ewave.data.country.CountryDto
import com.batynchuk.ewave.data.country.CountryNeighbourDto
import kotlinx.coroutines.Deferred
import javax.inject.Inject

interface NeighbourUseCase {
    fun getCountryNeighbours(countryName: String): Deferred<List<CountryNeighbourDto>>

    fun getAllCountries(): Deferred<List<CountryDto>>
}

class NeighboursFragmentUseCase @Inject constructor(private val countriesRepository: CountriesRepository) :
    NeighbourUseCase {
    override fun getAllCountries(): Deferred<List<CountryDto>> = countriesRepository.getAllCountries()


    override fun getCountryNeighbours(countryName: String): Deferred<List<CountryNeighbourDto>> =
        countriesRepository.getCountryNeighbours(countryName)
}