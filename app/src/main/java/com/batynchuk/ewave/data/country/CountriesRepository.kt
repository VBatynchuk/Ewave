package com.batynchuk.ewave.data.country

import kotlinx.coroutines.Deferred
import javax.inject.Inject


interface CountriesRepository {
    fun getAllCountries(): Deferred<List<CountryDto>>

    fun getCountryNeighbours(countryName: String): Deferred<List<CountryNeighbourDto>>
}


class CountriesRemoteRepositoryImpl @Inject constructor(private val countriesApi: CountriesApi) :
    CountriesRepository {
    override fun getCountryNeighbours(countryName: String): Deferred<List<CountryNeighbourDto>> =
        countriesApi.getCountryNeighbours(countryName)


    override fun getAllCountries() = countriesApi.getAllCountriesName()

}