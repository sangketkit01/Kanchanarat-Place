package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class Contract(
    @Expose
    @SerializedName("contract_id") val contractId : Int,

    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("reservation_id") val reservationId : Int,

    @Expose
    @SerializedName("contract_detail") val contractDetail : String,

    @Expose
    @SerializedName("contract_length_month") val contractLength : Int,

    @Expose
    @SerializedName("contract_path") val contractPath : String,

    @Expose
    @SerializedName("slip_path") val slipPath : String,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp,

    @Expose
    @SerializedName("expire_at") val expireAt : Timestamp,

    @Expose
    @SerializedName("status_id") val statusId : Int
) : Parcelable
