package com.example.capstone.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.viewmodel.DACViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    // screen content
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.doctor_app_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(270.dp)
                    .padding(bottom = 90.dp),
            )

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
                    containerColor = Color.White,
                ),
                shape = RoundedCornerShape(20.dp)
            )

            TextField(
                modifier = Modifier
                    .width(250.dp)
                    .padding(bottom = 50.dp),
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
                    containerColor = Color.White,

                    ),
                // hides password
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    navController.navigate("myAppointments")
                },
                modifier = Modifier.width(160.dp),
                shape = RoundedCornerShape(20),
            ) {
                Icon(
                    painter = painterResource(R.drawable.login_24dp),
                    contentDescription = stringResource(id = R.string.login_icon_desc),
                    modifier = Modifier.padding(end = 20.dp),
                )
                Text(text = stringResource(id = R.string.login), fontSize = 22.sp)
            }

            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.textButtonColors(),
                modifier = Modifier.height(35.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    style = TextStyle(
                        color = Color.Black,
                        textDecoration = TextDecoration.Underline
                    ), fontSize = 16.sp
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.healthcare_providers),
                style = TextStyle(
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                ), fontSize = 16.sp
            )
        }
    }
}