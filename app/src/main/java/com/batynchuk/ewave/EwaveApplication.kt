package com.batynchuk.ewave

import com.batynchuk.ewave.di.app.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class EwaveApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }

}