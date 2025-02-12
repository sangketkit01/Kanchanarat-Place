package com.src.kanchanaratplace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.src.kanchanaratplace.ApartmentContractScreen
import com.src.kanchanaratplace.AvailableRoomScreen
import com.src.kanchanaratplace.FirstScreen
import com.src.kanchanaratplace.ApartmentDetailScreen
import com.src.kanchanaratplace.ApartmentScreen
import com.src.kanchanaratplace.ChatScreen
import com.src.kanchanaratplace.ContractFeeDetailScreen
import com.src.kanchanaratplace.ContractFeeScreen
import com.src.kanchanaratplace.ContractPayQRScreen
import com.src.kanchanaratplace.ContractPaymentStatusScreen
import com.src.kanchanaratplace.LoginScreen
import com.src.kanchanaratplace.MakeReservationScreen
import com.src.kanchanaratplace.NotificationScreen
import com.src.kanchanaratplace.PayReservationQRScreen
import com.src.kanchanaratplace.PayReservationScreen
import com.src.kanchanaratplace.ProfileScreen
import com.src.kanchanaratplace.ReservationDetailScreen
import com.src.kanchanaratplace.ReservationPaymentStatusScreen
import com.src.kanchanaratplace.ReservationScreen
import com.src.kanchanaratplace.ReservationStatusScreen
import com.src.kanchanaratplace.SettingScreen

@Composable
fun NavGraph(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.First.route
    ) {
        composable(Screen.Login.route){
            LoginScreen(navController)
        }

        composable(Screen.Setting.route) {
            SettingScreen(navController)
        }

        composable(Screen.First.route) {
            FirstScreen(navController)
        }

        composable(Screen.Apartment.route){
            ApartmentScreen(navController)
        }

        composable(Screen.Chat.route){
            ChatScreen(navController)
        }


        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }

        composable(Screen.Notification.route){
            NotificationScreen(navController)
        }

        composable(Screen.ApartmentDetail.route) {
            ApartmentDetailScreen(navController)
        }

        composable(Screen.ApartmentContract.route){
            ApartmentContractScreen(navController)
        }

        composable(Screen.AvailableRoom.route) {
            AvailableRoomScreen(navController)
        }

        composable(Screen.Reservation.route) {
            ReservationScreen(navController)
        }

        composable(Screen.MakeReservation.route) {
            MakeReservationScreen(navController)
        }

        composable(Screen.PayReservation.route) {
            PayReservationScreen(navController)
        }

        composable(Screen.ReservationDetail.route) {
            ReservationDetailScreen(navController)
        }

        composable(Screen.PayReservationQR.route) {
            PayReservationQRScreen(navController)
        }

        composable(Screen.ReservationPaymentStatus.route) {
            ReservationPaymentStatusScreen(navController)
        }

        composable(Screen.ReservationStatus.route) {
            ReservationStatusScreen(navController)
        }

        composable(Screen.ContractFee.route) {
            ContractFeeScreen(navController)
        }

        composable(Screen.ContractFeeDetail.route) {
            ContractFeeDetailScreen(navController)
        }

        composable(Screen.ContractPayQR.route){
            ContractPayQRScreen(navController)
        }

        composable(Screen.ContractPaymentStatus.route) {
            ContractPaymentStatusScreen(navController)
        }
    }
}