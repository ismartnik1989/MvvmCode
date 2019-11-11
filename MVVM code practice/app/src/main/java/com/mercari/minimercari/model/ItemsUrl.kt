package com.mercari.minimercari.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Mukesh Kumar on 10/30/2019.
 */
class ItemsUrl : Serializable {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("data")
    @Expose
    var data: String? = null

}