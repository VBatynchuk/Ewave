package com.batynchuk.ewave.data.country

data class CountryDto(
    val name: String?,
    val nativeName: String?,
    val alpha3Code: String?
)

data class CountryNeighbourDto(
    val name: String?,
    val nativeName: String?,
    val borders: List<String>?
)