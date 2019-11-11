package com.mercari.minimercari.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
open class BaseActivity : AppCompatActivity() {

     private val fragmentManager : FragmentManager?= supportFragmentManager
    private var fragmentTransaction : FragmentTransaction?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

     open fun setFragment(
         fragmentId: Int, fragment: Fragment,
         fragmentName:String, isBackStackAllow: Boolean) {
         fragmentTransaction = fragmentManager?.beginTransaction()
         fragmentTransaction?.replace(fragmentId,fragment,fragmentName)
         fragmentTransaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
         if(isBackStackAllow){
             fragmentTransaction?.addToBackStack(fragmentName)
         }
         fragmentTransaction?.commit()

     }



}