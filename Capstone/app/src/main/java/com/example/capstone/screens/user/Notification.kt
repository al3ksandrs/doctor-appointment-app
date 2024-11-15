package com.example.capstone.screens.user

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
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
import androidx.core.app.NotificationCompat
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.model.Appointment
import com.example.capstone.data.utils.Utils
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.data.viewmodel.UserViewmodel
import com.example.capstone.ui.theme.ContainerGray
import java.sql.Time
import java.util.Calendar
import java.util.concurrent.TimeUnit

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
    val context = navController.context
    var hours by remember { mutableStateOf(0) }
    val username = userViewmodel.getLoggedInUsername()

    // create notification channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "appointment_reminder_channel"
        val channelName = "Appointment Reminders"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        val notificationManager: NotificationManager =
            navController.context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }

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

                                // schedule notification
                                val notificationTime = calculateNotificationTime(date, time, hours)
                                scheduleNotification(context, notificationTime, hours)

//                                // NOTIFICATION DEBUG
//                                val date = Date(notificationTime)
//                                Log.d("notification time:", date.toString())

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

// calculate the time difference for notifications
fun calculateNotificationTime(date: String, time: String, hoursBefore: Int): Long {
    val calendar = Calendar.getInstance()
    val appointmentDate = Utils.dateFormatter.parse(date)
    val appointmentTime = Utils.timeFormatter.parse(time)

    calendar.time = appointmentDate
    calendar.set(Calendar.HOUR_OF_DAY, appointmentTime.hours)
    calendar.set(Calendar.MINUTE, appointmentTime.minutes)

    val reminderTime = calendar.timeInMillis - TimeUnit.HOURS.toMillis(hoursBefore.toLong())
    return reminderTime
}

// schedule a notification with the calculated time above
fun scheduleNotification(context: Context, reminderTime: Long, hours: Int) {
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(context, "appointment_reminder_channel")
        .setContentTitle("Doctor's appointment reminder")
        .setContentText("Your doctor's appointment is in $hours hours!")
        .setSmallIcon(R.drawable.doctor_app_logo)
        .setWhen(reminderTime)
        .build()

    val notificationId = 1
    notificationManager.notify(notificationId, notification)
}