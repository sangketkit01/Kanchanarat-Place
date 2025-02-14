package com.src.kanchanaratplace.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Member(
    @Expose
    @SerializedName("member_id") val memberId : Int,

    @Expose
    @SerializedName("role_name") val role : String,

    @Expose
    @SerializedName("room_code") val room : String,

    @Expose
    @SerializedName("username") val username : String,

    @Expose
    @SerializedName("name") val name : String,

    @Expose
    @SerializedName("email") val email : String,

    @Expose
    @SerializedName("phone") val phone : String,

    @Expose
    @SerializedName("card_number") val cardNumber : String
)

