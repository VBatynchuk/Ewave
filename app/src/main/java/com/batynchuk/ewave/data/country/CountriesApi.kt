package com.batynchuk.ewave.data.country

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("all?fields=nativeName;name;alpha3Code")
    fun getAllCountriesName(): Deferred<List<CountryDto>>

    @GET("name/{name}?fields=nativeName;name;borders")
    fun getCountryNeighbours(@Path("name") name: String): Deferred<List<CountryNeighbourDto>>

}