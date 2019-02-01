package com.batynchuk.ewave.di.app

import android.content.Context
import com.batynchuk.ewave.EwaveApplication
import com.batynchuk.ewave.common.Workers
import com.batynchuk.ewave.di.ActivityScope
import com.batynchuk.ewave.screens.main.MainActivity
import com.batynchuk.ewave.screens.main.di.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
abstract class ApplicationModule {

    @Singleton
    @Binds
    @ApplicationContext
    abstract fun provideApplicationContext(ewaveApp: EwaveApplication): Context

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideWorkers(): Workers = Workers()
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity


}