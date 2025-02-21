package com.src.kanchanaratplace.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.src.kanchanaratplace.R

sealed class Screen(val route: String, val name: String, val icon: Any?){
    data object Login:Screen("login_screen","เข้าสู่ระบบ",null)
    data object Setting:Screen("setting_screen","ตั้งค่า",Icons.Default.Settings)

    data object First:Screen("first_screen","หน้าแรก", R.drawable.home)
    data object Apartment:Screen("apartment_screen","หอพัก",R.drawable.apartmentt)
    data object Chat:Screen("chat_screen","แชต",R.drawable.chat)
    data object Profile:Screen("profile_screen","โปรไฟล์",R.drawable.profile_icon)
    data object Notification:Screen("notification_screen","แจ้งเตือน",R.drawable.notification)

    data object ApartmentDetail:Screen("apartment_detail_screen","รายละเอียดหอพัก",null)
    data object ApartmentContract:Screen("apartment_contract","ช่องทางการติดต่อหอพัก",null)
    data object AvailableRoom:Screen("available_room_screen","ห้องพักที่ว่าง",null)

    data object QrCode:Screen("qr_code_screen","แสกนคิวอาร์โค้ด",null)
    data object Status:Screen("status_screen","สถานะ",null)

    data object Reservation:Screen("reservation_screen","จองห้องพัก",null)
    data object ReservationDetail:Screen("reservation_detail_screen","กรอกรายละเอียดการจอง",null)
    data object MakeReservation:Screen("make_reservation_screen","ทำการจองห้องพัก",null)
    data object PayReservation:Screen("pay_reservation_screen","ชำระค่าจองห้องพัก",null)
    data object ReservationStatus:Screen("reservation_status_screen","สถานะการจองหอพัก",null)
    data object CheckReservation:Screen("check_reservation_screen","ตรวจสอบการจองห้องพัก",null)

    data object ContractFee:Screen("contract_fee_screen","ค่าสัญญาหอพัก",null)
    data object ContractFeeDetail:Screen("contract_fee_detail_screen","รายละเอียดสัญญาหอพัก",null)

    data object MemberApartment:Screen("member_apartment_screen","หอพัก",R.drawable.apartmentt)
    data object MemberBill:Screen("member_bill_screen","บิลค่าห้อง",null)
    data object MemberBillDetail:Screen("member_bill_detail_screen","รายละเอียดบิลค่าห้อง",null)
    data object MemberCheckBill:Screen("member_check_bill_screen","จ่ายบิลค่าห้อง",null)

    data object News:Screen("news_screen","ข่าวสาร",null)
    data object NewsDetail:Screen("news_detail_screen","รายละเอียดข่าวสาร",null)

    data object HomeAdmin:Screen("home_admin_screen","หน้าหลัก",R.drawable.home)
    data object ProfileAdmin:Screen("profile_admin_screen","โปรไฟล์",R.drawable.profile_icon)

    data object MemberDataList:Screen("member_data_list_screen","ข้อมูลผู้เช่า",null)

    data object Bills:Screen("bills_screen","ค่าบริการ",R.drawable.admin_bill_menu)
    data object BillEdit:Screen("bill_edit_screen","แก้ไขบิล",null)
    data object ReservedList:Screen("reserved_list_screen","การจอง",R.drawable.admin_reservation_menu)
    data object ReservedDetail:Screen("reserved_detail_screen","รายละเอียดของการจอง",null)
    data object ContractList:Screen("contract_list_screen","ลิสต์สัญญาใหม่",null)
    data object ContractDetail:Screen("contract_detail_screen","รายละเอียดการทำสัญญา",null)
}