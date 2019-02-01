package com.batynchuk.ewave.di.app

import com.batynchuk.ewave.EwaveApplication
import com.batynchuk.ewave.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        RepositoryModule::class,
        RestModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<EwaveApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: EwaveApplication): Builder

        fun build(): ApplicationComponent
    }
}