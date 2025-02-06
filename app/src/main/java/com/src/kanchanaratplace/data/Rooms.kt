package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rooms(
    @Expose
    @SerializedName("room_id")
    val roomId : Int ,

    @Expose
    @SerializedName("room_code")
    val code : String ,

    @Expose
    @SerializedName("status_name")
    val status : String ,

    @Expose
    @SerializedName("room_price")
    val price : Int ,

    @Expose
    @SerializedName("room floor")
    val floor : Int
)
