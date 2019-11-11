package com.mercari.minimercari.adapter

import android.app.Activity
import android.graphics.Point
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mercari.minimercari.R
import com.mercari.minimercari.model.Items
import com.mercari.minimercari.utills.Utill
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class ItemsAdapter(activity: Activity, itemList: ArrayList<Items>) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolderRecycler>() {

    private var activity: Activity? = null
    private var itemList: ArrayList<Items>? = null

    init {
        try {
            this.activity = activity
            this.itemList = itemList

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecycler {
        val itemView = activity!!.layoutInflater.inflate(R.layout.recycler_view_items, parent, false)
        return ViewHolderRecycler(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderRecycler, position: Int) {


        val item = itemList!![position]
        val display = activity!!.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x / 2

        if (item != null) {
            if (item.photo != null) {
                Picasso.with(activity).load(item.photo).resize(width, 0).placeholder(R.drawable.icon_launcher)
                    .into(holder.itemImage)
            }
            if (item.status != null && item.status.equals("sold_out", ignoreCase = true)) {
                holder.bannerImage.visibility = View.VISIBLE
            } else {
                holder.bannerImage.visibility = View.GONE
            }
            if (item.name != null) {
                holder.itemName.text = item.name
            }

            if (item.numLikes != null) {
                holder.likeNumber.text = item.numLikes.toString()
            }

            if (item.numComments != null) {
                holder.commentNumber.text = item.numComments.toString()
            }

            if (item.price != null) {
                holder.amount.text = Utill.currentCurrent + " " + item.price.toString()
            }
        }


    }


    override fun getItemCount(): Int {
        return if (itemList != null && itemList!!.size > 0) {
            itemList!!.size
        } else
            0
    }


    /**
     * The type View holder recycler.
     */
    inner class ViewHolderRecycler(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var itemImage: ImageView
        lateinit var bannerImage: ImageView
        lateinit var itemName: TextView
        lateinit var likeNumber: TextView
        lateinit var commentNumber: TextView
        lateinit var amount: TextView

        init {
            try {
                itemImage = view.findViewById<View>(R.id.item_image) as ImageView
                bannerImage = view.findViewById<View>(R.id.banner_image) as ImageView
                itemName = view.findViewById<View>(R.id.item_text) as TextView
                likeNumber = view.findViewById<View>(R.id.like_text_number) as TextView
                commentNumber = view.findViewById<View>(R.id.comment_text_number) as TextView
                amount = view.findViewById<View>(R.id.cost_text) as TextView

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }


}

