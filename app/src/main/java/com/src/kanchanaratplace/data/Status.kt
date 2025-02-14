package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Status(
//    val statusId : Int , val statusName : String
    @Expose
    @SerializedName("status_id") val statusId : String,

    @Expose
    @SerializedName("status_name") val name : String
)
