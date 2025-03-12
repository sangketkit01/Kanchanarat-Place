package com.src.kanchanaratplace.navigation

import android.transition.Scene
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

    data object EditProfile:Screen("edit_profile_screen","แก้ไขโปรไฟล์",null)
    data object ProfileDetail:Screen("profile_detail_screen","รายละเอียดโปรไฟล์",null)

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
    data object ContractPaper:Screen("contract_paper_screen","พิมพ์ใบทำสัญญา",null)

    data object MemberApartment:Screen("member_apartment_screen","หอพัก",R.drawable.apartmentt)
    data object MemberBill:Screen("member_bill_screen","บิลค่าห้อง",null)
    data object MemberBillDetail:Screen("member_bill_detail_screen","รายละเอียดบิลค่าห้อง",null)
    data object MemberCheckBill:Screen("member_check_bill_screen","จ่ายบิลค่าห้อง",null)
    data object Receipt:Screen("receipt_screen","ใบเสร็จ",null)
    data object MemberContract:Screen("member_contrat_screen","สัญญา",null);
    data object RepairForm:Screen("repair_form_screen","แบบฟอร์มการแจ้งซ่อม",null)
    data object News:Screen("news_screen","ข่าวสาร",null)
    data object NewsDetail:Screen("news_detail_screen","รายละเอียดข่าวสาร",null)

    data object HomeAdmin:Screen("home_admin_screen","หน้าหลัก",R.drawable.home)
    data object ProfileAdmin:Screen("profile_admin_screen","โปรไฟล์",R.drawable.profile_icon)

    data object RepairList:Screen("repair_list_screen","การซ่อม",null)
    data object RepairPending:Screen("repair_pending_screen","กำลังดำเนินการซ่อม",null)
    data object RepairSuccess:Screen("repair_success_screen","ซ่อมเสร็จแล้ว",null)
    data object RepairDetail:Screen("repair_detail_screen","รายละเอียดการแจ้งซ่อม",null)

    data object LeavingForm:Screen("leaving_form_screen","การขอย้ายออก",null)
    data object LeavingList:Screen("leaving_list_screen","ลิสต์การร้องขอย้ายออก",null)

    data object MemberDataList:Screen("member_data_list_screen","ลิสต์ข้อมูลผู้เช่า",null)
    data object MemberDataDetail:Screen("member_data_detail_screen","รายละเอียดข้อมูลผู้เช่า",null)

    data object ContractPaperMember:Screen("contract_paper_member_screen","ใบสัญญา",null)

    data object Bills:Screen("bills_screen","ค่าบริการ",R.drawable.admin_bill_menu)
    data object BillEdit:Screen("bill_edit_screen","แก้ไขบิล",null)
    data object ReservedList:Screen("reserved_list_screen","การจอง",R.drawable.admin_reservation_menu)
    data object ReservedDetail:Screen("reserved_detail_screen","รายละเอียดของการจอง",null)
    data object ContractList:Screen("contract_list_screen","ลิสต์สัญญาใหม่",null)
    data object ContractDetail:Screen("contract_detail_screen","รายละเอียดการทำสัญญา",null)
    data object ContractDetailPaper:Screen("contract_detail_paper","ใบสัญญา",null)

    data object Meter:Screen("meter_screen","จดมิเตอร์",R.drawable.meter_menu)
    data object MeterWater:Screen("meter_water_screen","มิเตอร์น้ำ",null)
    data object MeterElectricity:Screen("meter_electricity_screen","มิเตอร์ไฟฟ้า",null)

    data object CheckMemberPayment:Screen("check_member_payment_screen","ตรวจสอบสถานะการชำระเงิน",null)
    data object CheckMemberPaymentDetail:Screen("check_member_payment_detail_screen","รายละเอียดการชำระเงิน",null)
}