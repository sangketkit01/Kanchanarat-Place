package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repair(
    @Expose
    @SerializedName("repair_id") val repairId : Int,

    @Expose
    @SerializedName("room_code") val room : String,

    @Expose
    @SerializedName("repair_detail") val detail : String,

    @Expose
    @SerializedName("repair_cost") val cost : Int,

    @Expose
    @SerializedName("status_name") val status : String
)
