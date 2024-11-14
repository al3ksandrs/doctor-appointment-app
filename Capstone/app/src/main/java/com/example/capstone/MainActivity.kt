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
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.model.User
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.screens.Screens
import com.example.capstone.screens.user.AppointmentDetails
import com.example.capstone.screens.user.CalendarView
import com.example.capstone.screens.user.CancelAppointment
import com.example.capstone.screens.user.ChangeTimeslot
import com.example.capstone.screens.user.Login
import com.example.capstone.screens.user.MyAppointments
import com.example.capstone.screens.user.Notification
import com.example.capstone.screens.user.Register
import com.example.capstone.screens.user.TimeslotDetails
import com.example.capstone.screens.user.TimeslotSelect
import com.example.capstone.ui.theme.CapstoneTheme
import com.example.capstone.ui.theme.LightBlueBackground
import java.sql.Time
import java.util.Date

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
            MyAppointments(navController, viewModel, generateDummyAppointments())
        }
        composable(route = Screens.ChangeTimeslot.route) {
            ChangeTimeslot(navController, viewModel)
        }
        composable(route = "cancelAppointment/{appointmentID}") { backStackEntry ->
            val appointmentID = backStackEntry.arguments?.getString("appointmentID")?.toInt()
            // TODO: get appointment through viewmodel and pass it to the cancellation screen
            CancelAppointment(navController, generateDummyAppointments()[1], viewModel)
        }
        composable(route = Screens.CalendarView.route) {
            CalendarView(navController, viewModel)
        }
        composable(route = Screens.TimeslotSelect.route) {
            TimeslotSelect(navController, viewModel)
        }
        composable(route = Screens.TimeslotDetails.route) {
            TimeslotDetails(navController, viewModel)
        }
        composable(route = Screens.AppointmentDetails.route) {
            AppointmentDetails(navController, viewModel)
        }
        composable(route = Screens.Notification.route) {
            Notification(navController, viewModel)
        }
    }
}

// dummy data generated for testing NOTE TO SELF: DELETE LATER
fun generateDummyAppointments(): List<Appointment> {
    return listOf(
        Appointment(
            userID = 1,
            date = Date(2024 - 1900, 9, 13),
            time = Time(12, 0, 0),
            location = "Amsterdam Hospital",
            doctor = "Dr. John Doe"
        ),
        Appointment(
            userID = 2,
            date = Date(2024 - 1900, 10, 14),
            time = Time(15, 30, 0),
            location = "Utrecht Clinics",
            doctor = "Dr. Jane Smith"
        )
    )
}