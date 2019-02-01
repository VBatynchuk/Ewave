package com.batynchuk.ewave.common.base

import android.content.Context
import android.os.Bundle
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    val safeContext: Context
        get() {
            return context ?: throw NullPointerException("Context is null!")
        }

    val safeArguments: Bundle
        get() {
            return arguments ?: throw NullPointerException("Arguments are null!")
        }

}