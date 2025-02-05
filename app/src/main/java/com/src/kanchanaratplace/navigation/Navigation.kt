package com.src.kanchanaratplace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.src.kanchanaratplace.AvailableRoomScreen
import com.src.kanchanaratplace.FirstScreen
import com.src.kanchanaratplace.ApartmentDetailScreen
import com.src.kanchanaratplace.MakeReservationScreen
import com.src.kanchanaratplace.ProfileScreen
import com.src.kanchanaratplace.ReservationScreen

@Composable
fun NavGraph(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.First.route
    ) {
        composable(Screen.First.route) {
            FirstScreen(navController)
        }

        composable(Screen.Profile.route){
            ProfileScreen(navController)
        }

        composable(Screen.ApartmentDetail.route) {
            ApartmentDetailScreen(navController)
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
    }
}