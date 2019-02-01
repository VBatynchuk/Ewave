package com.batynchuk.ewave.common.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val fragmentContainerId: Int

    fun show(fragment: Fragment, backStackTag: String?, isAnimated: Boolean) {
        buildTransaction(fragment, backStackTag, isAnimated)
            .commit()
    }

    open fun buildTransaction(fragment: Fragment, backStackTag: String?, isAnimated: Boolean): FragmentTransaction {
        val transaction = supportFragmentManager.beginTransaction()

        if (isAnimated) {
            setAnimation(transaction)
        }

        transaction.replace(fragmentContainerId, fragment, fragment.javaClass.simpleName)

        if (backStackTag != null) {
            transaction.addToBackStack(backStackTag)
        }

        return transaction
    }

    open fun setAnimation(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0)
    }

    fun popBackStack() = supportFragmentManager.popBackStack()


}