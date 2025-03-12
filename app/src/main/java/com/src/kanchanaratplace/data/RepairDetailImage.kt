package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class RepairDetailImage(
    @Expose
    @SerializedName("repair_detail_image_id") val repairDetailImageId : Int,

    @Expose
    @SerializedName("repair_id") val repairId : Int,

    @Expose
    @SerializedName("repair_detail_image_path") val repairDetailImagePath : String,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp
) : Parcelable
