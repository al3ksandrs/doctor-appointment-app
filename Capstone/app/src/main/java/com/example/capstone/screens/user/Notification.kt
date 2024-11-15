package com.example.capstone.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.capstone.data.utils.Utils
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.data.viewmodel.UserViewmodel
import com.example.capstone.ui.theme.ContainerGray
import java.sql.Time
import java.util.Calendar
import java.util.Date

@Composable
fun Notification(
    navController: NavHostController,
    viewModel: DACViewModel,
    userViewmodel: UserViewmodel,
    date: String,
    time: String,
    healthIssue: String,
    isUrgent: Boolean
) {
    var hours by remember { mutableStateOf(0) }
    val username = userViewmodel.getLoggedInUsername()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        // top text
        Text(
            text = "$date - $time",
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 5.dp),
        )

        // screen content
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(350.dp)
                    .padding(35.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.do_you_want_notification),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = stringResource(id = R.string.how_long_before_appointment),
                    fontSize = 16.sp,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // hour back button
                    IconButton(
                        onClick = {
                            hours = (hours - 1).let { if (it < 0) 23 else it }
                        },
                        modifier = Modifier.padding(end = 40.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.day_back_24dp),
                            contentDescription = stringResource(id = R.string.back),
                            modifier = Modifier
                                .size(30.dp),
                            tint = Color.Black
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // hours
                        Text(
                            text = "$hours",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(end = 10.dp)
                        )

                        // hours text
                        Text(
                            text = stringResource(id = R.string.hours),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    // hour forward button
                    IconButton(
                        onClick = {
                            hours = (hours + 1).let { if (it > 24) 0 else it }
                        },
                        modifier = Modifier.padding(start = 40.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.day_forward_24dp),
                            contentDescription = stringResource(id = R.string.forward),
                            modifier = Modifier
                                .size(30.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        // bottom buttons
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            // finish appointment button
            Button(
                onClick = {
                    if (username != null) {
                        userViewmodel.getUserID(username) { userID ->
                            if (userID != null) {
                                // format date and time for use in Database
                                val appointmentDate = Utils.dateFormatter.parse(date)
                                val appointmentTime = Utils.timeFormatter.parse(time)?.let { Time(it.time) }

                                val appointment = Appointment(
                                    userID = userID,
                                    date = appointmentDate!!,
                                    time = appointmentTime!!,
                                    location = "Utrecht Clinics",
                                    doctor = "Dr. Pannings",
                                    healthIssue = healthIssue,
                                    voiceMemo = "",
                                    isItUrgent = isUrgent
                                )

                                // insert appointment into database
                                viewModel.insertAppointment(appointment)

                                // navigate back to myAppointments when done
                                navController.navigate("myAppointments")
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(180.dp)
                    .height(70.dp)
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ContainerGray,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.check_24dp),
                    contentDescription = stringResource(id = R.string.finish_appointment),
                    modifier = Modifier.padding(end = 15.dp),
                )
                Text(
                    text = stringResource(id = R.string.finish_appointment), fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}