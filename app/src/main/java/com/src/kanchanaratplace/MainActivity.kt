package com.src.kanchanaratplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.src.kanchanaratplace.navigation.NavGraph
import com.src.kanchanaratplace.ui.theme.KanchanaratPlaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KanchanaratPlaceTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}



