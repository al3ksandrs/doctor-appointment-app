package com.example.capstone.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.capstone.R
import com.example.capstone.data.viewmodel.DACViewModel

@Composable
fun Placeholder(
    navController: NavHostController,
    viewmodel: DACViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ){
        // top text
        Text(
            text = stringResource(id = R.string.placeholder),
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
                .padding(top = 70.dp)
                .height(620.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){

        }

        // bottom buttons
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){

        }
    }
}