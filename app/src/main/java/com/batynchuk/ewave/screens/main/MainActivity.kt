package com.batynchuk.ewave.screens.main

import android.os.Bundle
import com.batynchuk.ewave.R
import com.batynchuk.ewave.common.base.BaseActivity
import com.batynchuk.ewave.screens.countries.CountriesFragment

class MainActivity : BaseActivity() {

    override val fragmentContainerId: Int
        get() = R.id.flContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show(CountriesFragment(), null, false)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
