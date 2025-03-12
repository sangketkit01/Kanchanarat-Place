package com.src.kanchanaratplace.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp
import java.util.Date

@Parcelize
data class Member(
    @Expose
    @SerializedName("member_id") val memberId : Int,

    @Expose
    @SerializedName("room_id") val roomId : Int,

    @Expose
    @SerializedName("role_id") val roleId : Int,

    @Expose
    @SerializedName("username") val username : String,

    @Expose
    @SerializedName("password") val password : String,

    @Expose
    @SerializedName("name") val name : String,

    @Expose
    @SerializedName("email") val email : String,

    @Expose
    @SerializedName("phone") val phone : String,

    @Expose
    @SerializedName("line") val line : String,

    @Expose
    @SerializedName("card_number") val cardNumber : String,

    @Expose
    @SerializedName("image_path") val imagePath : String,

    @Expose
    @SerializedName("birth_date") val birthDate : Date,

    @Expose
    @SerializedName("created_at") val createdAt : Timestamp,

    @Expose
    @SerializedName("updated_at") val updatedAt : Timestamp
) : Parcelable

