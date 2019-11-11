package com.mercari.minimercari.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.mercari.minimercari.R
import com.mercari.minimercari.adapter.SectionsPagerAdapter
import com.mercari.minimercari.base.BaseFragment
import com.mercari.minimercari.model.ItemsUrl
import com.mercari.minimercari.utills.CustomProgressDialog
import com.mercari.minimercari.viewmodel.PageViewModel

/**
 * Created by Mukesh Kumar on 11/6/2019.
 */
class ItemsFragment : BaseFragment() {

    private var tabs: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var fab: FloatingActionButton? = null
    private var itemsTabList: ArrayList<ItemsUrl>? = null
    private val CURRENT_POSITION_KEY: String = "CURRENT_POSITION_KEY"


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_items, container, false)
        setLayoutView(view)
        setTabApi()
        setListener()

        return view;
    }
    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(CURRENT_POSITION_KEY, viewPager!!.getCurrentItem())
        super.onSaveInstanceState(savedInstanceState)
    }


    private fun setLayoutView(view: View?) {

        tabs = view?.findViewById(R.id.tabs)
        viewPager = view?.findViewById(R.id.view_pager)
        fab = view?.findViewById(R.id.fab)


    }

    private fun setTabApi() {

        CustomProgressDialog.showProgressDialog(activity!!)

        var pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java!!)
        pageViewModel.init()

        pageViewModel.allTabs.observe(this, Observer<List<ItemsUrl>> {
            CustomProgressDialog.hideProgressDialog()
            itemsTabList?.clear()
            itemsTabList = it as ArrayList<ItemsUrl>
            if (itemsTabList != null && itemsTabList!!.size > 0)
                setTabAdapter(itemsTabList)

        })


    }


    private fun setTabAdapter(itemsTabList: ArrayList<ItemsUrl>?) {
        viewPager?.adapter = SectionsPagerAdapter(context!!, activity!!.supportFragmentManager, itemsTabList)
        tabs?.setupWithViewPager(viewPager)
        viewPager!!.offscreenPageLimit = 2;
    }

    private fun setListener() {

        fab?.setOnClickListener { view ->
            Snackbar.make(view, "This is camera button.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }


}