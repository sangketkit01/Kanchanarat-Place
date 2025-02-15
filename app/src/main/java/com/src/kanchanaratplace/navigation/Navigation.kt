package com.src.kanchanaratplace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.src.kanchanaratplace.screen.contract.ContractFeeDetailScaffold
import com.src.kanchanaratplace.screen.main.ApartmentContractScreen
import com.src.kanchanaratplace.screen.reservation.AvailableRoomScreen
import com.src.kanchanaratplace.screen.main.FirstScreen
import com.src.kanchanaratplace.screen.main.ApartmentDetailScreen
import com.src.kanchanaratplace.screen.main.ApartmentScreen
import com.src.kanchanaratplace.screen.main.ChatScreen
import com.src.kanchanaratplace.screen.reservation.CheckReservationScreen
import com.src.kanchanaratplace.screen.contract.ContractFeeDetailScreen
import com.src.kanchanaratplace.screen.contract.ContractFeeScaffold
import com.src.kanchanaratplace.screen.contract.ContractFeeScreen
import com.src.kanchanaratplace.screen.main.ApartmentContractScaffold
import com.src.kanchanaratplace.screen.main.ApartmentDetailScaffold
import com.src.kanchanaratplace.screen.main.ApartmentScaffold
import com.src.kanchanaratplace.screen.main.ChatScaffold
import com.src.kanchanaratplace.screen.main.FirstScaffold
import com.src.kanchanaratplace.screen.main.LoginScaffold
import com.src.kanchanaratplace.screen.main.LoginScreen
import com.src.kanchanaratplace.screen.main.NotificationScaffold
import com.src.kanchanaratplace.screen.reservation.MakeReservationScreen
import com.src.kanchanaratplace.screen.main.NotificationScreen
import com.src.kanchanaratplace.screen.main.ProfileScaffold
import com.src.kanchanaratplace.screen.reservation.PayReservationScreen
import com.src.kanchanaratplace.screen.main.ProfileScreen
import com.src.kanchanaratplace.screen.main.SettingScaffold
import com.src.kanchanaratplace.screen.share.QRCodeScreen
import com.src.kanchanaratplace.screen.reservation.ReservationDetailScreen
import com.src.kanchanaratplace.screen.reservation.ReservationScreen
import com.src.kanchanaratplace.screen.reservation.ReservationStatusScreen
import com.src.kanchanaratplace.screen.main.SettingScreen
import com.src.kanchanaratplace.screen.owner.BillEditScaffold
import com.src.kanchanaratplace.screen.owner.BillsScaffold
import com.src.kanchanaratplace.screen.owner.BillsScreen
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
import com.src.kanchanaratplace.screen.share.StatusScreen

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
    }
}
