package com.example.capstone.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.utils.Utils
import com.example.capstone.data.viewmodel.DACViewModel
import java.util.Calendar

@Composable
fun TimeslotSelect(
    navController: NavHostController,
    viewmodel: DACViewModel,
    date: String
) {
    // opening and closing times in hours
    val startHour = 9
    val endHour = 16

    // initial date we get from calendarView.kt
    val dateParts = date.split("-").map { it.toInt() }
    val initialDate = Calendar.getInstance().apply {
        set(dateParts[0] - 1, dateParts[1] - 2, dateParts[2] - 1)
    }

    // date state as milliseconds so recomposition is triggered
    var currentDateMilliS by remember { mutableStateOf(initialDate.timeInMillis) }

    // format date using dateFormatter in the utils class
    val formattedDate = remember(currentDateMilliS) {
        Utils.dateFormatter.format(currentDateMilliS)
    }

    // sample availability list (TODO: remove later since its for demo purposes)
    val availabilityList = List(endHour - startHour + 1) { true }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp),
        ) {
            // day back button
            IconButton(
                onClick = {
                    currentDateMilliS = Calendar.getInstance().apply {
                        timeInMillis = currentDateMilliS
                        add(Calendar.DAY_OF_MONTH, -1)
                    }.timeInMillis
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

            // top text
            Text(
                text = formattedDate,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 5.dp),
            )

            // day forward button
            IconButton(
                onClick = {
                    currentDateMilliS = Calendar.getInstance().apply {
                        timeInMillis = currentDateMilliS
                        add(Calendar.DAY_OF_MONTH, 1)
                    }.timeInMillis
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

        // screen content
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 70.dp)
                .height(620.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(endHour - startHour + 1) { hour ->
                    val timeSlot = String.format("%02d:00", startHour + hour)

                    val isOpen = availabilityList[hour]

                    Button(
                        onClick = { if(isOpen){
                            // pass the date and time to the TimeslotDetails screen
                            val timeSlot = String.format("%02d:00", startHour + hour)
                            navController.navigate("timeslotDetails/$formattedDate/$timeSlot")
                        } },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 35.dp)
                            .height(80.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = timeSlot,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center,

                            )

                            Text(
                                text = if (isOpen) stringResource(id = R.string.open) else stringResource(id = R.string.closed),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                            )

                                Icon(
                                    painter = painterResource(R.drawable.arrow_forward_24dp),
                                    contentDescription = stringResource(id = R.string.continue_action),
                                    modifier = Modifier.padding(end = 15.dp, top = 2.dp)
                                        .size(35.dp)
                                        // transparent icon if isOpen is false so the user doesn't see an arrow icon
                                        .alpha(if (isOpen) 1f else 0f),
                                )
                        }
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