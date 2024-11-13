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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.ui.theme.ContainerGray
import com.example.capstone.ui.theme.MicRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentDetails(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    var healthIssue by remember { mutableStateOf("") }
    var isUrgent by remember { mutableStateOf(false) }

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
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Text(stringResource(id = R.string.describe_health_issue), fontSize = 22.sp)
                TextField(
                    value = healthIssue,
                    onValueChange = { healthIssue = it },
                    placeholder = { Text(stringResource(id = R.string.type_here)) },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
                    colors = TextFieldDefaults.textFieldColors(
                        // underline removal
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        // container
                        containerColor = ContainerGray,
                    ),
                )
                Text(
                    stringResource(id = R.string.voice_note),
                    fontSize = 16.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .background(MicRed, shape = RoundedCornerShape(20))
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.mic_24dp),
                            contentDescription = stringResource(id = R.string.placeholder),
                        )
                    }
                    Text(stringResource(id = R.string.hold_to_record))
                }
                Text(stringResource(id = R.string.urgent), fontSize = 16.sp)
                Row {
                    Button(
                        onClick = { isUrgent = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(stringResource(id = R.string.yes), fontSize = 20.sp, textDecoration = if (isUrgent) TextDecoration.Underline else TextDecoration.None)
                    }
                    Button(
                        onClick = { isUrgent = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(stringResource(id = R.string.no), fontSize = 20.sp,textDecoration = if (!isUrgent) TextDecoration.Underline else TextDecoration.None)
                    }
                }

                // next step button
                Button(
                    onClick = {
                        navController.popBackStack()
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
                        contentDescription = stringResource(id = R.string.next_step),
                        modifier = Modifier.padding(end = 15.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.next_step), fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}