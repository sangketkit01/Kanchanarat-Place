package com.src.kanchanaratplace.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.src.kanchanaratplace.screen.contract.ContractFeeDetailScaffold
import com.src.kanchanaratplace.screen.contract.ContractFeeScaffold
import com.src.kanchanaratplace.screen.main.ApartmentContractScaffold
import com.src.kanchanaratplace.screen.main.ApartmentDetailScaffold
import com.src.kanchanaratplace.screen.owner.ApartmentScaffold
import com.src.kanchanaratplace.screen.main.ChatScaffold
import com.src.kanchanaratplace.screen.main.FirstScaffold
import com.src.kanchanaratplace.screen.main.LoginScaffold
import com.src.kanchanaratplace.screen.main.NotificationScaffold
import com.src.kanchanaratplace.screen.main.ProfileScaffold
import com.src.kanchanaratplace.screen.main.SettingScaffold
import com.src.kanchanaratplace.screen.member.MemberApartmentScaffold
import com.src.kanchanaratplace.screen.member.MemberBillDetailScaffold
import com.src.kanchanaratplace.screen.member.MemberBillScaffold
import com.src.kanchanaratplace.screen.member.MemberCheckBIllScaffold
import com.src.kanchanaratplace.screen.owner.BillEditScaffold
import com.src.kanchanaratplace.screen.owner.BillsScaffold
import com.src.kanchanaratplace.screen.owner.ContractDetailScaffold
import com.src.kanchanaratplace.screen.owner.ContractListScaffold
import com.src.kanchanaratplace.screen.owner.ReservedDetailScaffold
import com.src.kanchanaratplace.screen.owner.ReservedListScaffold
import com.src.kanchanaratplace.screen.reservation.AvailableRoomScaffold
import com.src.kanchanaratplace.screen.reservation.CheckReservationScaffold
import com.src.kanchanaratplace.screen.reservation.MakeReservationScaffold
import com.src.kanchanaratplace.screen.reservation.PayReservationScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationDetailScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationScaffold
import com.src.kanchanaratplace.screen.reservation.ReservationStatusScaffold
import com.src.kanchanaratplace.screen.share.QrCodeScaffold
import com.src.kanchanaratplace.screen.share.StatusScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.First.route
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
    }
}
