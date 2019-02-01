package com.batynchuk.ewave.screens.countries

import com.batynchuk.ewave.common.EwaveRouter
import com.batynchuk.ewave.screens.neighbours.NeighboursFragment
import javax.inject.Inject

interface CountriesRouter {
    fun showNeighbours(countryName: String)
}

class CountriesFragmentRouter @Inject constructor(private val ewaveRouter: EwaveRouter) : CountriesRouter {
    override fun showNeighbours(countryName: String) {
        ewaveRouter.show(NeighboursFragment.getInstance(countryName), NeighboursFragment::class.toString())
    }
}