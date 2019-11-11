package com.mercari.minimercari.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercari.minimercari.model.Items
import com.mercari.minimercari.networking.GetDataService
import com.mercari.minimercari.networking.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class ItemsViewModel : ViewModel() {

    private var newsApi: GetDataService? = null
    private var allDataLiveData: MutableLiveData<List<Items>>? = null
    private var menDataLiveData: MutableLiveData<List<Items>>? = null
    private var womenLiveData: MutableLiveData<List<Items>>? = null

    fun init() {

        if (newsApi != null) {
            return
        }
        newsApi = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "on cleared called")
    }
    val allData: MutableLiveData<List<Items>>?
        get() {

            if (allDataLiveData != null && allDataLiveData?.value !=null && allDataLiveData?.value!!.size>0) {
                return allDataLiveData
            }else{
                allDataLiveData = MutableLiveData()

                newsApi!!.allData.enqueue(object : Callback<List<Items>> {
                    override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                        if (response.isSuccessful) {
                            allDataLiveData?.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                        allDataLiveData?.value = null
                    }
                })
                return allDataLiveData
            }

        }

    val menData: MutableLiveData<List<Items>>?
        get() {

            if (menDataLiveData != null && menDataLiveData?.value !=null && menDataLiveData?.value!!.size>0) {
                return menDataLiveData
            }else{
                menDataLiveData = MutableLiveData()

                newsApi!!.menData.enqueue(object : Callback<List<Items>> {
                    override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                        if (response.isSuccessful) {
                            menDataLiveData?.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                        menDataLiveData?.value = null
                    }
                })
                return menDataLiveData
            }

        }


    val womenData: MutableLiveData<List<Items>>?
        get() {

            if (womenLiveData != null && womenLiveData?.value !=null && womenLiveData?.value!!.size>0) {
                return womenLiveData
            }else{
                womenLiveData = MutableLiveData()

                newsApi!!.allWomen.enqueue(object : Callback<List<Items>> {
                    override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                        if (response.isSuccessful) {
                            womenLiveData?.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                        womenLiveData?.value = null
                    }
                })
                return womenLiveData
            }
        }


}
