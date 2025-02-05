package com.src.kanchanaratplace.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val name: String, val icon: ImageVector?){
    data object First:Screen("first_screen","หน้าแรก", Icons.Default.Home)
    data object Apartment:Screen("apartment_screen","หอพัก",Icons.Default.Favorite)
    data object Chat:Screen("chat_screen","แชต",Icons.Default.Call)
    data object Profile:Screen("profile_screen","โปรไฟล์",Icons.Default.AccountCircle)
    data object Notification:Screen("notification_screen","แจ้งเตือน",Icons.Default.Notifications)

    data object ApartmentDetail:Screen("apartment_detail_screen","รายละเอียดหอพัก",null)
    data object AvailableRoom:Screen("available_room_screen","ห้องพักที่ว่าง",null)
    data object Reservation:Screen("reservation_screen","จองห้องพัก",null)
    data object MakeReservation:Screen("make_reservation_screen","ทำการจองห้องพัก",null)
}