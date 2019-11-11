package com.mercari.minimercari.view

import android.os.Bundle
import com.mercari.minimercari.R
import com.mercari.minimercari.base.BaseActivity

/**
 * Created by Mukesh Kumar on 11/06/2019.
 */
class ItemsActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val fragment = ItemsFragment()
        setFragment(R.id.fragment_layout, fragment, getString(R.string.items_fragment), false)

    }






}