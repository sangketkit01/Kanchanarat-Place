package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Role(
//    val roleId : Int , val name : String
    @Expose
    @SerializedName("role_id") val roleId : Int,

    @Expose
    @SerializedName("role_name") val name : String
)
