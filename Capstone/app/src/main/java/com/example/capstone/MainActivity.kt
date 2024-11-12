package com.example.capstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.screens.Screens
import com.example.capstone.screens.user.Login
import com.example.capstone.screens.user.MyAppointments
import com.example.capstone.screens.user.Register
import com.example.capstone.ui.theme.CapstoneTheme
import com.example.capstone.ui.theme.LightBlueBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LightBlueBackground
                ) {
                    DACNavHost(navController = rememberNavController(), modifier = Modifier)
                }
            }
        }
    }
}

@Composable
private fun DACNavHost(
    navController: NavHostController, modifier: Modifier
) {
    val viewModel: DACViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier
    ) {
        composable(route = Screens.Login.route) {
            Login(navController, viewModel)
        }
        composable(route = Screens.Register.route) {
            Register(navController, viewModel)
        }
        composable(route = Screens.MyAppointments.route) {
            MyAppointments(navController, viewModel)
        }
    }
}