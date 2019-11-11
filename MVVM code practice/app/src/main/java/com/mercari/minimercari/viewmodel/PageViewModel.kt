package com.mercari.minimercari.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mercari.minimercari.model.ItemsUrl
import com.mercari.minimercari.networking.GetDataService
import com.mercari.minimercari.networking.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class PageViewModel : ViewModel() {

    private var newsApi: GetDataService? = null
    private var tabMutableLiveList = MutableLiveData<List<ItemsUrl>>()
    private var tabMutableLive = MutableLiveData<ItemsUrl>()

    fun init() {
        if (newsApi == null)
            newsApi = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)

    }


    val allTabs: MutableLiveData<List<ItemsUrl>>
        get() {
            if (tabMutableLiveList?.value != null && tabMutableLiveList?.value!!.isNotEmpty()) {
                return tabMutableLiveList;
            } else {
                tabMutableLiveList = MutableLiveData()
                newsApi!!.masterUrl.enqueue(object : Callback<List<ItemsUrl>> {
                    override fun onResponse(call: Call<List<ItemsUrl>>, response: Response<List<ItemsUrl>>) {
                        if (response.isSuccessful) {
                            tabMutableLiveList.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<List<ItemsUrl>>, t: Throwable) {
                        tabMutableLiveList.value = null
                    }
                })
                return tabMutableLiveList
            }
        }

    val text: LiveData<String> = Transformations.map(tabMutableLiveList) {
        "section: $it"
    }

    fun setUrlIndex(itemsUrl: ItemsUrl?) {
        tabMutableLive.value = itemsUrl
    }

    fun getUrlIndex(): ItemsUrl? {
        return tabMutableLive?.value
    }


}