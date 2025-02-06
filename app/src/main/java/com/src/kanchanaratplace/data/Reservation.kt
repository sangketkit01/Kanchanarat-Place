package com.src.kanchanaratplace.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Reservation(val reservationId : Int , val status : String , val name : String,
    val phone : String , val email : String , val line : String)

@Parcelize
data class MakeReservation(val name : String , val surname : String,
    val phone:String , val email: String) : Parcelable
