package com.mercari.minimercari.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/"

    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}
