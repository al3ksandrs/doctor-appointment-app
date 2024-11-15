package com.example.capstone.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.utils.Utils.Companion.dateFormatter
import com.example.capstone.data.utils.Utils.Companion.timeFormatter
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.data.viewmodel.UserViewmodel
import com.example.capstone.ui.theme.ContainerGray

@Composable
fun MyAppointments(
    navController: NavHostController,
    viewmodel: DACViewModel,
    appointments: List<Appointment>,
    userViewmodel: UserViewmodel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        // top text
        Text(
            text = stringResource(id = R.string.my_appointments),
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 5.dp),
        )

        // appointment list NOTE TO SELF: CHANGE COLUMN TO LAZYCOLUMN WHEN DATA IS ADDED
        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
                .height(620.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(appointments) { appointment ->
                val formattedDate = dateFormatter.format(appointment.date)
                val formattedTime = timeFormatter.format(appointment.time)

                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .width(350.dp)
                        .padding(35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row {
                            Text(
                                text = formattedDate,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(text = " - ", fontSize = 22.sp, fontWeight = FontWeight.Medium)
                            Text(text = formattedTime, fontSize = 22.sp, fontWeight = FontWeight.Medium)
                        }
                        Text(text = "Location:", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        Text(text = appointment.location)
                        Text(text = "Doctor: ", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        Text(text = appointment.doctor, modifier = Modifier.padding(bottom = 35.dp))
                    }

                    // change time of appointment
                    Button(
                        onClick = {
                            navController.navigate("changeTimeslot")
                        },
                        modifier = Modifier
                            .width(180.dp)
                            .height(80.dp)
                            .padding(bottom = 20.dp),
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ContainerGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.timeslot_24dp),
                            contentDescription = stringResource(id = R.string.change_timeslot),
                            modifier = Modifier.padding(end = 15.dp),
                        )
                        Text(
                            text = stringResource(id = R.string.change_timeslot), fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    // cancel appointment button
                    Button(
                        onClick = {
                            navController.navigate("cancelAppointment/${appointment.id}")
                        },
                        modifier = Modifier
                            .width(180.dp),
                        shape = RoundedCornerShape(20),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ContainerGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.cancel_24dp),
                            contentDescription = stringResource(id = R.string.cancel_appointment),
                            modifier = Modifier.padding(end = 15.dp),
                        )
                        Text(
                            text = stringResource(id = R.string.cancel_appointment),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // new appointment button
            Button(
                onClick = {
                    navController.navigate("calendarView")
                },
                modifier = Modifier
                    .width(180.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_24dp),
                    contentDescription = stringResource(id = R.string.new_appointment),
                    modifier = Modifier.padding(end = 15.dp),
                )
                Text(
                    text = stringResource(id = R.string.new_appointment), fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            // logout button
            Button(
                onClick = {
                    // clear login state so user doesn't stay logged in
                    userViewmodel.saveLoginState(false)

                    // navigate to the login screen
                    navController.navigate("login") {
                        // clear backstack so user can't just go back to myAppointments if he isn't logged in
                        popUpTo("myAppointments") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout_24dp),
                    contentDescription = stringResource(id = R.string.logout),
                    modifier = Modifier.padding(end = 15.dp),
                )
                Text(
                    text = stringResource(id = R.string.logout), fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}