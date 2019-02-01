package com.batynchuk.ewave.screens.neighbours.di

import androidx.lifecycle.ViewModelProviders
import com.batynchuk.ewave.di.FragmentScope
import com.batynchuk.ewave.screens.neighbours.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NeighboursFragmentModule{

    @Binds
    @FragmentScope
    abstract fun bindNeighboursUseCase(neighboursFragmentUseCase: NeighboursFragmentUseCase): NeighbourUseCase

    @Binds
    @FragmentScope
    abstract fun bindNeighbourRouter(neighbourFragmentRouter: NeighbourFragmentRouter): NeighbourRouter

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideNeighboursViewModel(
            fragment: NeighboursFragment,
            neighboursViewModelFactory: NeighboursViewModelFactory
        ): NeighbourViewModel {
            return ViewModelProviders.of(fragment, neighboursViewModelFactory).get(NeighbourViewModel::class.java)
        }
    }

}

