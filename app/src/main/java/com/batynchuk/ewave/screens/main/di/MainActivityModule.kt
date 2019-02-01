package com.batynchuk.ewave.screens.main.di

import com.batynchuk.ewave.common.EwaveNavigationRouter
import com.batynchuk.ewave.common.EwaveRouter
import com.batynchuk.ewave.common.base.BaseActivity
import com.batynchuk.ewave.di.ActivityScope
import com.batynchuk.ewave.screens.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [FragmentBindingModule::class])
interface MainActivityModule {

    @Binds
    @ActivityScope
    fun bindRouter(ewaveNavigationRouter: EwaveNavigationRouter): EwaveRouter

    @Binds
    @ActivityScope
    fun binBaseActivity(mainActivity: MainActivity) : BaseActivity

//    @Module
//    companion object {
//        @JvmStatic
//        @Provides
//        @ActivityScope
//        fun provideRouter(mainActivity: MainActivity): EwaveRouter = EwaveNavigationRouter(mainActivity)
//    }

}