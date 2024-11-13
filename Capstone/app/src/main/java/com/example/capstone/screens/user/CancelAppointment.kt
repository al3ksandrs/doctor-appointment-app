package com.example.capstone.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.capstone.ui.theme.ContainerGray

@Composable
fun CancelAppointment(
    navController: NavHostController,
    appointment: Appointment,
    viewModel: DACViewModel
) {
    val formattedDate = dateFormatter.format(appointment.date)
    val formattedTime = timeFormatter.format(appointment.time)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Text(
                text = formattedDate,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium
            )
            Text(text = " - ", fontSize = 26.sp, fontWeight = FontWeight.Medium)
            Text(text = formattedTime, fontSize = 26.sp, fontWeight = FontWeight.Medium)
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
        ) {
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
                Text(
                    text = stringResource(id = R.string.cancel_confirmation),
                    fontSize = 20.sp
                )

                // yes, cancel button
                Button(
                    onClick = {
                        // TODO: get viewmodel to delete the appointment by ID
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(top = 50.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ContainerGray,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.close_24dp),
                        contentDescription = stringResource(id = R.string.yes_cancel),
                        modifier = Modifier.padding(end = 15.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.yes_cancel),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
                    )
                }

                // no, go back button
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(top = 15.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ContainerGray,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.undo_24dp),
                        contentDescription = stringResource(id = R.string.no_go_back),
                        modifier = Modifier.padding(end = 15.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.no_go_back),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}