package com.danielp4.fragmentmanager.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.danielp4.fragmentmanager.R

object SupportFragmentManager {

    fun nextFragment(nextFragment: Fragment, tag: String, requireActivity: FragmentActivity) {
        val transaction = requireActivity.supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.place_holder_1, nextFragment)
            .addToBackStack(tag)
            .commit()
    }

    fun previousFragment(tag: String?, requireActivity: FragmentActivity) {
        if (tag != null) {
            requireActivity.supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            requireActivity.supportFragmentManager.popBackStack()
        }
    }

    fun nextFragment2(nextFragment: Fragment, requireActivity: FragmentActivity) {
        val transaction = requireActivity.supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.place_holder_2, nextFragment)
            .addToBackStack(null)
            .commit()
    }

}