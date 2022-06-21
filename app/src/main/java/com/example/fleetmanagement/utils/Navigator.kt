package com.example.fleetmanagement.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.fleetmanagement.R

object Navigator {
    fun replace(fragment: Fragment,fragmentActivity: FragmentActivity){
        val transaction = fragmentActivity.supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun add(fragment: Fragment,fragmentActivity: FragmentActivity){
        val transaction = fragmentActivity.supportFragmentManager.beginTransaction().add(R.id.frameLayout,fragment)
        transaction.commit()
    }
}