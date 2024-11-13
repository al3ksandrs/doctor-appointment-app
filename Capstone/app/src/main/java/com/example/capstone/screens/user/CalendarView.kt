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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.utils.Utils
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.screens.other.InfiniteCircularList
import java.util.Calendar

@Composable
fun CalendarView(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    // lists for day month and year
    val dayList = (1..31).map { it.toString().padStart(2, '0') }
    val monthList = (1..12).map { it.toString().padStart(2, '0') }
    val yearList = (2020..2030).map { it.toString() }

    // initial values
    val initialDay = Utils.dateFormatter.format(Calendar.getInstance().time).substring(0, 2)
    val initialMonth = Utils.dateFormatter.format(Calendar.getInstance().time).substring(3, 5)
    val initialYear = Utils.dateFormatter.format(Calendar.getInstance().time).substring(6)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        // top text
        Text(
            text = stringResource(id = R.string.select_a_day),
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 5.dp),
        )

        // screen content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(350.dp)
                .height(200.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                ),
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // day
                InfiniteCircularList(
                    width = 80.dp,
                    itemHeight = 40.dp,
                    items = dayList,
                    initialItem = initialDay,
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                    textColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    onItemSelected = { index, selectedDay ->
                        // TODO: LOGIC
                    }
                )

                // month
                InfiniteCircularList(
                    width = 70.dp,
                    itemHeight = 40.dp,
                    items = monthList,
                    initialItem = initialMonth,
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                    textColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    onItemSelected = { index, selectedMonth ->
                        // TODO: LOGIC
                    }
                )

                // year
                InfiniteCircularList(
                    width = 90.dp,
                    itemHeight = 40.dp,
                    items = yearList,
                    initialItem = initialYear,
                    textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                    textColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    onItemSelected = { index, selectedYear ->
                        // TODO: LOGIC
                    }
                )
            }
        }

        // bottom buttons
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            // continue button
            Button(
                onClick = {
                    navController.navigate("calendarView")
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
                    painter = painterResource(R.drawable.arrow_forward_24dp),
                    contentDescription = stringResource(id = R.string.continue_action),
                    modifier = Modifier.padding(end = 15.dp),
                )
                Text(
                    text = stringResource(id = R.string.continue_action), fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

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