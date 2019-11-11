package com.mercari.minimercari.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mercari.minimercari.adapter.ItemsAdapter
import com.mercari.minimercari.model.Items
import com.mercari.minimercari.model.ItemsUrl
import com.mercari.minimercari.utills.CustomProgressDialog
import com.mercari.minimercari.viewmodel.ItemsViewModel
import com.mercari.minimercari.viewmodel.PageViewModel
/**
 * Created by Mukesh Kumar on 11/06/2019.
 */

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var itemList: ArrayList<Items>? = null
    private var mRecyclerView: RecyclerView? = null


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
     //   private const val ARG_SECTION_NUMBER = "section_number"
        private const val ARG_SECTION_NAME = "section_name"
        private const val ALL = "All"
        private const val MEN = "Men"
        private const val WOMEN = "Women"
        private lateinit var type: String

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(itemsUrl:  ItemsUrl?): PlaceholderFragment {

            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_SECTION_NAME, itemsUrl)

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {

            setUrlIndex(arguments?.getSerializable(ARG_SECTION_NAME) as ItemsUrl)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(com.mercari.minimercari.R.layout.fragment_main, container, false)

        setLayoutRef(root)
        setApiRepoData(arguments?.getSerializable(ARG_SECTION_NAME) as ItemsUrl)

        return root
    }


    private fun setLayoutRef(view: View) {
        mRecyclerView = view.findViewById(com.mercari.minimercari.R.id.item_recycler_view)
    }

    private fun setApiRepoData(itemsUrl: ItemsUrl?) {
        CustomProgressDialog.showProgressDialog(activity!!)
        var itemsViewModel = ViewModelProviders.of(this).get(ItemsViewModel::class.java!!)
        itemsViewModel.init()


        when (itemsUrl?.name) {
            MEN -> {

                itemsViewModel.menData?.observe(this, Observer<List<Items>> {
                    CustomProgressDialog.hideProgressDialog()
                    itemList?.clear()
                    itemList = it as ArrayList<Items>
                    if (itemList != null && itemList!!.size>0)
                        setRecyclerViewData(itemList)

                })
            }
            ALL -> {
                itemsViewModel.allData?.observe(this, Observer<List<Items>> {
                    CustomProgressDialog.hideProgressDialog()
                    itemList?.clear()
                    itemList = it as ArrayList<Items>
                    if (itemList != null && itemList!!.size>0)
                        setRecyclerViewData(itemList)

                })
            }
            WOMEN-> {
                itemsViewModel.womenData?.observe(this, Observer<List<Items>> {
                    CustomProgressDialog.hideProgressDialog()
                    itemList?.clear()
                    itemList = it as ArrayList<Items>
                    if (itemList != null && itemList!!.size>0)
                        setRecyclerViewData(itemList)

                })
            }
        }

    }

    private fun setRecyclerViewData(itemList: ArrayList<Items>?) {
        mRecyclerView?.layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        mRecyclerView?.adapter = ItemsAdapter(activity!!, itemList!!)
        mRecyclerView?.itemAnimator = DefaultItemAnimator()
    }




}