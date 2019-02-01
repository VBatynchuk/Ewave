package com.batynchuk.ewave.common

import androidx.fragment.app.Fragment
import com.batynchuk.ewave.common.base.BaseActivity
import javax.inject.Inject


interface EwaveRouter {
    fun show(fragment: Fragment, backStackTag: String? = null, isAnimated: Boolean = false)

    fun goBack()
}

class EwaveNavigationRouter @Inject constructor(private val baseActivity: BaseActivity) : EwaveRouter {
    override fun show(fragment: Fragment, backStackTag: String?, isAnimated: Boolean) {
        baseActivity.show(fragment, backStackTag, isAnimated)
    }

    override fun goBack() {
        baseActivity.popBackStack()
    }
}