package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class Repair(
    @Expose
    @SerializedName("repair_id") val repairId : Int,

    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("repair_title") val repairTitle : String,

    @Expose
    @SerializedName("repair_detail") val repairDetail : String,

    @Expose
    @SerializedName("repair_cost") val repairCost : Int,

    @Expose
    @SerializedName("status_id") val statusId : Int,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp,

    @Expose
    @SerializedName("updated_at") val updatedAt : Timestamp
) : Parcelable
