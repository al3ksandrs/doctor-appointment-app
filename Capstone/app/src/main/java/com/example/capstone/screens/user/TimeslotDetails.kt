package com.example.capstone.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.ui.theme.ContainerGray

@Composable
fun TimeslotDetails(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        // top text
        Text(
            text = "24/09/2024 - 16:00",
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Location:", fontSize = 25.sp)
                Text("Utrecht Clinics")
                Text("Doctor:", fontSize = 25.sp)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Dr. Pannings")
                    Text("+31 6 534234433")
                    Text("drpannings@gmail.com")
                }
                Text("Notes:", fontSize = 25.sp)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("- Free parking")
                    Text("- Public transport")
                }

                // make appointment button
                Button(
                    onClick = {
                        navController.navigate("appointmentDetails")
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
                        painter = painterResource(R.drawable.arrow_forward_24dp),
                        contentDescription = stringResource(id = R.string.make_appointment),
                        modifier = Modifier.padding(end = 15.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.make_appointment), fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
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
            // go back button
            Button(
                onClick = {
                    navController.popBackStack()
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
                    painter = painterResource(R.drawable.undo_24dp),
                    contentDescription = stringResource(id = R.string.go_back),
                    modifier = Modifier.padding(end = 15.dp),
                )
                Text(
                    text = stringResource(id = R.string.go_back), fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}