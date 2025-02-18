package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rooms(
    @Expose
    @SerializedName("room_id")
    val roomId : Int ,

    @Expose
    @SerializedName("room_code")
    val code : String ,

    @Expose
    @SerializedName("status_name")
    val status : String ,

    @Expose
    @SerializedName("room_price")
    val price : Int ,

    @Expose
    @SerializedName("room floor")
    val floor : Int
) : Parcelable

@Parcelize
data class DefaultRooms(
    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("room_code") val roomCode : String,

    @Expose
    @SerializedName("status_id") val statusId : Int,

    @Expose
    @SerializedName("room_price") val roomPrice : Int,

    @Expose
    @SerializedName("room_floor") val roomFloor : Int
) : Parcelable


