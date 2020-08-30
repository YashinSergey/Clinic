package com.yashinsergey.clinic.common

import androidx.fragment.app.FragmentManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

interface FragmentExtensions {

    fun FragmentManager.display(
        @IdRes containerId: Int, fragment: Fragment,
        transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
        skipHistory: Boolean = false
    ) {
        executePendingTransactions()
        val fragmentTransaction = this.beginTransaction()
        if(fragments.size > 0 && !skipHistory) {
            fragmentTransaction.addToBackStack("")
        }
        fragmentTransaction.replace(containerId, fragment, "")
        fragmentTransaction.setTransition(transition)
        fragmentTransaction.commit()
    }
}