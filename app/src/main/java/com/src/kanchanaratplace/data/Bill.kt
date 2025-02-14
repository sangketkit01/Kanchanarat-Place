package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.util.Date

data class Bill(
//    val billId : Int , val room : String , val waterUsed : Int,
//    val electricityUsed : Int , val totalPrice : Int , val date : Date ,
//    val createdAt : Timestamp
    @Expose
    @SerializedName("bill_id") val billId : Int,

    @Expose
    @SerializedName("room_code") val room : String,

    @Expose
    @SerializedName("water_used") val waterUsed : Int,

    @Expose
    @SerializedName("electricity_used") val electricityUsed : Int ,

    @Expose
    @SerializedName("total_price") val totalPrice : Int,

    @Expose
    @SerializedName("date") val date: Date,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp
)
