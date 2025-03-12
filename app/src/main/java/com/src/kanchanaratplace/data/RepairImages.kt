package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class RepairImages(
    @Expose
    @SerializedName("image_id") val imageId : Int,

    @Expose
    @SerializedName("image_path") val imagePath : String,

    @Expose
    @SerializedName("repair_id") val repairId : Int,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp
) : Parcelable