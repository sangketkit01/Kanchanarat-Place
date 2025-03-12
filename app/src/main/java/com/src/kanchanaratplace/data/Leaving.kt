package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.util.Date

@Parcelize
data class Leaving(
    @Expose
    @SerializedName("leaving_id") val leavingId : Int,

    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("report_date") val reportDate : String,

    @Expose
    @SerializedName("moveout_date") val moveOutDate : String,

    @Expose
    @SerializedName("reason") val reason : String,

    @Expose
    @SerializedName("other_detail") val otherDetail : String?,

    @Expose
    @SerializedName("status_id") val statusId : Int,

    @Expose
    @SerializedName("reject_reason") val rejectReason : String?,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp,

    @Expose
    @SerializedName("updated_at") val updatedAt : Timestamp?
) : Parcelable
