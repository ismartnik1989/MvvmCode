package com.mercari.minimercari.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mercari.minimercari.model.ItemsUrl
import com.mercari.minimercari.view.PlaceholderFragment
/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
/*
private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)
*/


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,itemsTabList : ArrayList<ItemsUrl>?) : FragmentPagerAdapter(fm) {

    private var itemsTabList: java.util.ArrayList<ItemsUrl>? = null
    private var mContext: Context? = null


    init {
        try {
            this.mContext = context
            this.itemsTabList = itemsTabList

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(itemsTabList?.get(position))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return itemsTabList?.get(position)?.name
    }

    override fun getCount(): Int {
        return itemsTabList!!.size
    }
}