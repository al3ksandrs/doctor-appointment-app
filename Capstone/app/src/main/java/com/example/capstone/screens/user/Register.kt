package com.example.capstone.screens.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.viewmodel.DACViewModel
import com.example.capstone.ui.theme.ContainerGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        // back button
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 15.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_24dp),
                contentDescription = "Back to start screen",
                modifier = Modifier.size(40.dp)
            )
        }

        // top text
        Text(
            text = stringResource(id = R.string.register),
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 5.dp),
        )

        // page content
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            TextField(
                modifier = Modifier.width(250.dp),
                value = username,
                onValueChange = {
                    username = it
                },
                label = { Text(stringResource(id = R.string.username), color = Color.Black) },
                placeholder = { Text(text = "") },
                colors = TextFieldDefaults.textFieldColors(
                    // underline removal
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    // container
                    containerColor = ContainerGray,
                ),
                shape = RoundedCornerShape(20.dp)
            )

            TextField(
                modifier = Modifier
                    .width(250.dp),
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text(stringResource(id = R.string.password), color = Color.Black) },
                placeholder = { Text(text = "") },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    // underline removal
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    // container
                    containerColor = ContainerGray,
                )
            )

            TextField(
                modifier = Modifier
                    .width(250.dp),
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(stringResource(id = R.string.email), color = Color.Black) },
                placeholder = { Text(text = "") },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    // underline removal
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    // container
                    containerColor = ContainerGray,
                )
            )

            TextField(
                modifier = Modifier
                    .width(250.dp),
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                label = { Text(stringResource(id = R.string.phone_number), color = Color.Black) },
                placeholder = { Text(text = "") },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    // underline removal
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    // container
                    containerColor = ContainerGray,
                )
            )
        }

        // register button
        Button(
            onClick = {
                navController.navigate("myAppointments")
            },
            modifier = Modifier
                .width(180.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            shape = RoundedCornerShape(20),
        ) {
            Icon(
                painter = painterResource(R.drawable.login_24dp),
                contentDescription = stringResource(id = R.string.register),
                modifier = Modifier.padding(end = 20.dp),
            )
            Text(text = stringResource(id = R.string.register), fontSize = 22.sp)
        }
    }
}