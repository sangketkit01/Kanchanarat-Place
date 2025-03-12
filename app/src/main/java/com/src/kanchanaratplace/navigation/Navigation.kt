package com.src.kanchanaratplace.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.src.kanchanaratplace.screen.contract.ContractFeeDetailScaffold
import com.src.kanchanaratplace.screen.contract.ContractFeeScaffold
import com.src.kanchanaratplace.screen.contract.ContractPaperScaffold
import com.src.kanchanaratplace.screen.main.ApartmentContractScaffold
import com.src.kanchanaratplace.screen.main.ApartmentDetailScaffold
import com.src.kanchanaratplace.screen.owner.ApartmentScaffold
import com.src.kanchanaratplace.screen.main.ChatScaffold
import com.src.kanchanaratplace.screen.main.EditProfileScaffold
import com.src.kanchanaratplace.screen.main.FirstScaffold
import com.src.kanchanaratplace.screen.member.LeavingFormScaffold
import com.src.kanchanaratplace.screen.main.LoginScaffold
import com.src.kanchanaratplace.screen.main.NotificationScaffold
import com.src.kanchanaratplace.screen.main.ProfileDetailScaffold
import com.src.kanchanaratplace.screen.main.ProfileScaffold
import com.src.kanchanaratplace.screen.main.SettingScaffold
import com.src.kanchanaratplace.screen.member.MemberApartmentScaffold
import com.src.kanchanaratplace.screen.member.MemberBillDetailScaffold
import com.src.kanchanaratplace.screen.member.MemberBillScaffold
import com.src.kanchanaratplace.screen.member.MemberCheckBIllScaffold
import com.src.kanchanaratplace.screen.member.MemberContractScaffold
import com.src.kanchanaratplace.screen.member.NewsDetailScaffold
import com.src.kanchanaratplace.screen.member.NewsScaffold
import com.src.kanchanaratplace.screen.member.RepairFormScaffold
import com.src.kanchanaratplace.screen.owner.BillEditScaffold
import com.src.kanchanaratplace.screen.owner.BillsScaffold
import com.src.kanchanaratplace.screen.owner.CheckMemberPaymentDetailScaffold
import com.src.kanchanaratplace.screen.owner.CheckMemberPaymentScaffold
import com.src.kanchanaratplace.screen.owner.ContractDetailPaperScaffold
import com.src.kanchanaratplace.screen.owner.ContractDetailScaffold
import com.src.kanchanaratplace.screen.owner.ContractListScaffold
import com.src.kanchanaratplace.screen.owner.HomeAdminScaffold
import com.src.kanchanaratplace.screen.owner.LeavingListScaffold
import com.src.kanchanaratplace.screen.owner.MemberDataDetailScaffold
import com.src.kanchanaratplace.screen.owner.MemberDataListScaffold
import com.src.kanchanaratplace.screen.owner.MeterElectricityScaffold
import com.src.kanchanaratplace.screen.owner.MeterScaffold
import com.src.kanchanaratplace.screen.owner.MeterWaterScaffold
import com.src.kanchanaratplace.screen.owner.ProfileAdminScaffold
import com.src.kanchanaratplace.screen.owner.RepairDetailScaffold
import com.src.kanchanaratplace.screen.owner.RepairListScaffold
import com.src.kanchanaratplace.screen.owner.RepairPendingScaffold
import com.src.kanchanaratplace.screen.owner.RepairSuccessScaffold
import com.src.kanchanaratplace.screen.owner.ReservedDetailScaffold
import com.src.kanchanaratplace.screen.owner.ReservedListScaffold
import com.src.kanchanaratplace.screen.reservation.AvailableRoomScaffold
import com.src.kanchanaratplace.screen.reservation.CheckReservationScaffold
import com.src.kanchanaratplace.screen.reservation.MakeReservationScaffold
import com.src.kanchanaratplace.screen.reservation.PayReservationScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationDetailScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationStatusScaffold
import com.src.kanchanaratplace.screen.share.ContractPaperMemberScaffold
import com.src.kanchanaratplace.screen.share.QrCodeScaffold
import com.src.kanchanaratplace.screen.share.ReceiptScaffold
import com.src.kanchanaratplace.screen.share.StatusScaffold
import com.src.kanchanaratplace.session.MemberSharePreferencesManager
import com.src.kanchanaratplace.status.Role

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val sharePreferences = MemberSharePreferencesManager(context)
    NavHost(
        navController = navController,
        startDestination =
            if(sharePreferences.member?.roleId == Role.OWNER.id) Screen.HomeAdmin.route
            else Screen.First.route
    ) {
        composable(Screen.Login.route) {
            LoginScaffold(navController)
        }

        composable(Screen.Setting.route) {
            SettingScaffold(navController)
        }

        composable(Screen.First.route) {
            FirstScaffold(navController)
        }

        composable(Screen.Apartment.route) {
            ApartmentScaffold(navController)
        }

        composable(Screen.Chat.route) {
            ChatScaffold(navController)
        }

        composable(Screen.Profile.route) {
            ProfileScaffold(navController)
        }

        composable(Screen.Notification.route) {
            NotificationScaffold(navController)
        }

        composable(Screen.ApartmentDetail.route) {
           ApartmentDetailScaffold(navController)
        }

        composable(Screen.ApartmentContract.route) {
            ApartmentContractScaffold(navController)
        }

        composable(Screen.AvailableRoom.route) {
            AvailableRoomScaffold(navController)
        }

        composable(Screen.Reservation.route) {
            ReservationScaffold(navController)
        }

        composable(Screen.MakeReservation.route) {
            MakeReservationScaffold(navController)
        }

        composable(Screen.PayReservation.route) {
            PayReservationScaffold(navController)
        }

        composable(Screen.ReservationDetail.route) {
            ReservationDetailScaffold(navController)
        }

        composable(Screen.QrCode.route) {
            QrCodeScaffold(navController)
        }

        composable(Screen.ReservationStatus.route) {
            ReservationStatusScaffold(navController)
        }

        composable(Screen.CheckReservation.route){
            CheckReservationScaffold(navController)
        }

        composable(Screen.ContractFee.route) {
            ContractFeeScaffold(navController)
        }

        composable(Screen.ContractFeeDetail.route) {
            ContractFeeDetailScaffold(navController)
        }

        composable(Screen.Status.route) {
            StatusScaffold(navController)
        }

        composable(Screen.Bills.route) {
            BillsScaffold(navController)
        }

        composable(Screen.BillEdit.route) {
            BillEditScaffold(navController)
        }

        composable(Screen.ReservedList.route) {
            ReservedListScaffold(navController)
        }

        composable(Screen.ContractList.route) {
            ContractListScaffold(navController)
        }

        composable(Screen.ReservedDetail.route) {
            ReservedDetailScaffold(navController)
        }

        composable(Screen.ContractDetail.route) {
            ContractDetailScaffold(navController)
        }

        composable(Screen.ContractPaper.route){
            ContractPaperScaffold(navController)
        }

        composable(Screen.MemberApartment.route) {
            MemberApartmentScaffold(navController)
        }

        composable(Screen.MemberBill.route) {
            MemberBillScaffold(navController)
        }

        composable(Screen.MemberBillDetail.route) {
            MemberBillDetailScaffold(navController)
        }

        composable(Screen.MemberCheckBill.route) {
            MemberCheckBIllScaffold(navController)
        }

        composable(Screen.MemberContract.route){
            MemberContractScaffold(navController)
        }

        composable(Screen.Receipt.route){
            ReceiptScaffold(navController)
        }

        composable(Screen.News.route) {
            NewsScaffold(navController)
        }

        composable(Screen.NewsDetail.route) {
            NewsDetailScaffold(navController)
        }

        composable(Screen.RepairForm.route) {
            RepairFormScaffold(navController)
        }

        composable(Screen.HomeAdmin.route) {
            HomeAdminScaffold(navController)
        }

        composable(Screen.ProfileAdmin.route) {
            ProfileAdminScaffold(navController)
        }

        composable(Screen.MemberDataList.route) {
            MemberDataListScaffold(navController)
        }

        composable(Screen.MemberDataDetail.route){
            MemberDataDetailScaffold(navController)
        }

        composable(Screen.RepairList.route) {
            RepairListScaffold(navController)
        }

        composable(Screen.RepairPending.route) {
            RepairPendingScaffold(navController)
        }

        composable(Screen.RepairDetail.route) {
            RepairDetailScaffold(navController)
        }

        composable(Screen.RepairSuccess.route) {
            RepairSuccessScaffold(navController)
        }

        composable(Screen.LeavingForm.route) {
            LeavingFormScaffold(navController)
        }

        composable(Screen.LeavingList.route) {
            LeavingListScaffold(navController)
        }

        composable(Screen.ContractPaperMember.route) {
            ContractPaperMemberScaffold(navController)
        }

        composable(Screen.ContractDetailPaper.route) {
            ContractDetailPaperScaffold(navController)
        }

        composable(Screen.Meter.route) {
            MeterScaffold(navController)
        }

        composable(Screen.MeterWater.route) {
            MeterWaterScaffold(navController)
        }

        composable(Screen.MeterElectricity.route) {
            MeterElectricityScaffold(navController)
        }

        composable(Screen.CheckMemberPayment.route) {
            CheckMemberPaymentScaffold(navController)
        }

        composable(Screen.CheckMemberPaymentDetail.route) {
            CheckMemberPaymentDetailScaffold(navController)
        }

        composable(Screen.EditProfile.route) {
            EditProfileScaffold(navController)
        }

        composable(Screen.ProfileDetail.route){
            ProfileDetailScaffold(navController)
        }
    }
}
