package com.example.capstone.data.utils

import java.text.SimpleDateFormat
import java.util.Locale

class Utils {
    companion object{
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    }
}