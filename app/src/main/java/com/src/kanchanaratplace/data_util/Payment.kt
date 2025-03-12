package com.src.kanchanaratplace.data_util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Payment(
    val name : String?,
    val room : String?,
    val detail : String,
    val price : Int,
    val slipPath : String?
)

@Parcelize
data class Receipt(
    val name : String,
    val price: Int
) : Parcelable
