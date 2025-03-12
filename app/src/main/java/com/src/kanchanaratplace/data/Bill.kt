package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.util.Date

@Parcelize
data class Bill(
    @Expose
    @SerializedName("bill_id") val billId : Int?,

    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("previous_water_used") val previousWaterUsed : Int,

    @Expose
    @SerializedName("previous_electricity_used") val previousElectricityUsed : Int,

    @Expose
    @SerializedName("current_water_used") val currentWaterUsed : Int,

    @Expose
    @SerializedName("current_electricity_used") val currentElectricityUsed : Int,

    @Expose
    @SerializedName("water_used") val waterUsed : Int,

    @Expose
    @SerializedName("electricity_used") val electricityUsed : Int,

    @Expose
    @SerializedName("total_price") val totalPrice : Int,

    @Expose
    @SerializedName("slip_path") val slipPath : String?,

    @Expose
    @SerializedName("date") val date : Date,

    @Expose
    @SerializedName("status_id") val statusId : Int,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp,

    @Expose
    @SerializedName("updated_at") val updatedAt : Timestamp
) : Parcelable

data class BillRequest(
    @SerializedName("room_id") val roomId: Int,
    @SerializedName("water_used") val waterUsed: Float,
    @SerializedName("electricity_used") val electricityUsed: Float,
    @SerializedName("total_price") val totalPrice: Float,
    @SerializedName("date") val date: String,
    @SerializedName("status_id") val statusId: Int
)

