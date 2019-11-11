package com.mercari.minimercari.networking

import com.mercari.minimercari.model.Items
import com.mercari.minimercari.model.ItemsUrl
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
interface GetDataService {
    @get:GET("m-et/Android/json/master.json")
    val masterUrl: Call<List<ItemsUrl>>

    @get:GET("m-et/Android/json/all.json")
    val allData: Call<List<Items>>

    @get:GET("m-et/Android/json/men.json")
    val menData: Call<List<Items>>

    @get:GET("m-et/Android/json/women.json")
    val allWomen: Call<List<Items>>
}
