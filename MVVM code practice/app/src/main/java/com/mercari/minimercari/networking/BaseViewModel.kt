package com.mercari.minimercari.networking

import androidx.lifecycle.LiveData
import com.mercari.minimercari.model.Items

/**
 * Created by Mukesh Kumar on 11/7/2019.
 * SEW Pvt Ltd
 * mukesh.kumar@smartusys.com
 */
interface BaseViewModel {

    fun getUserName(): LiveData<List<Items>>
    fun saveUserName(itemsData: LiveData<List<Items>>?)
}