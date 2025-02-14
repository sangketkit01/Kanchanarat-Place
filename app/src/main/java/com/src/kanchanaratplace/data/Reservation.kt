package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Reservation(

    @Expose
    @SerializedName("reservation_id") val reservationId : Int,

    @Expose
    @SerializedName("status_code") val status : String,

    @Expose
    @SerializedName("name") val name : String,

    @Expose
    @SerializedName("phone") val phone : String,

    @Expose
    @SerializedName("email") val email : String,

    @Expose
    @SerializedName("line") val line : String
)

@Parcelize
data class MakeReservation(val name : String , val surname : String,
    val phone:String , val email: String) : Parcelable
