package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class RepairDetail(
    @Expose
    @SerializedName("repair_detail_id") val repairDetailId : Int,

    @Expose
    @SerializedName("repair_id") val repairId : Int,

    @Expose
    @SerializedName("repair_detail_detail") val repairDetailDetail : String,

    @Expose
    @SerializedName("repair_detail_cost_detail") val repairDetailCostDetail : String,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp
) : Parcelable
