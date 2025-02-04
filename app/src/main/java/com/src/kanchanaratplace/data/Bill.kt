package com.src.kanchanaratplace.data

import java.sql.Timestamp
import java.util.Date

data class Bill(val billId : Int , val room : String , val waterUsed : Int,
    val electricityUsed : Int , val totalPrice : Int , val date : Date ,
    val createdAt : Timestamp)
