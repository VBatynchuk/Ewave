package com.batynchuk.ewave.screens.neighbours

import com.batynchuk.ewave.common.EwaveRouter
import javax.inject.Inject

interface NeighbourRouter {
    fun goBack()
}

class NeighbourFragmentRouter @Inject constructor(private val ewaveRouter: EwaveRouter) : NeighbourRouter {
    override fun goBack() {
        ewaveRouter.goBack()
    }
}