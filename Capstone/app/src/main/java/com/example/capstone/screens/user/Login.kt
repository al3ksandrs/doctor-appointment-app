package com.example.capstone.screens.user

import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.capstone.data.viewmodel.UserViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController,
    viewmodel: DACViewModel,
    userViewmodel: UserViewmodel
) {
    // check if user is already logged in and if so they can pass the login screen
    if (userViewmodel.isUserLoggedIn()) {
        navController.navigate("myAppointments")
    }

    // get current context
    val context = LocalContext.current

    // Create a CoroutineScope for launching coroutines in Composable functions, here we use it for the login with Firebase
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(stringResource(id = R.string.email), color = Color.Black) },
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
//                    // Local room login
//                    // check if combination of username and password is correct
//                    userViewmodel.validateUser(username.text, password.text) { user ->
//                        if (user != null) {
//                            // save login state
//                            userViewmodel.saveLoginState(true, username.text)
//                            navController.navigate("myAppointments")
//                        } else {
//                            Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show()
//                        }
//                    }

                    // Log in of user through Firebase
                    coroutineScope.launch{
                        try {
                            userViewmodel.logInWithEmailAndPassword(email, password)
                            navController.navigate("myAppointments")
                        } catch (e: Exception){
                            println(e.message)
                        }
                    }
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