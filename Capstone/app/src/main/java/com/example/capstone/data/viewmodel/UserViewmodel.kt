package com.example.capstone.data.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.example.capstone.data.model.User
import com.example.capstone.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewmodel(application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepository(application)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // saving of user data so it can be used to verify if the user is already logged in
    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("user_prefs", Application.MODE_PRIVATE)

    // Registering of an user through Firebase Authentication
    suspend fun signUpWithEmailAndPassword(email: String, password: String): String {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            "Sign-up successful for user: ${user?.email}"
        } catch (e: Exception) {
            throw Exception("Sign-up failed: ${e.message}")
        }
    }

    // Logging in of an user through Firebase Authentication
    suspend fun logInWithEmailAndPassword(email: String, password: String): String {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            val user = auth.currentUser
            "Login successful for user: ${user?.email}"
        } catch (e: Exception) {
            throw Exception("Login failed: ${e.message}")
        }
    }

    // Get current logged in User from Firebase
    fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    // insert a user into the database
    fun insertUser(user: User) {
        mainScope.launch {
            userRepository.insertUser(user)
        }
    }

    // get user by their username
    fun getUserByUsername(username: String, onResult: (User?) -> Unit) {
        mainScope.launch {
            val user = userRepository.getUserByUsername(username)
            onResult(user)
        }
    }

    // get userID by using their username
    fun getUserID(username: String, onResult: (Long) -> Unit) {
        mainScope.launch {
            val userID = userRepository.getUserID(username)
            onResult(userID)
        }
    }

    // logging in of an user (validation of username and password)
    fun validateUser(username: String, password: String, onResult: (User?) -> Unit) {
        mainScope.launch {
            val user = userRepository.validateUser(username, password)
            // return user if one is found by searching for a combination of username and password
            onResult(user)
        }
    }

    // save login state
    fun saveLoginState(isLoggedIn: Boolean, userName: String? = null) {
        with(sharedPreferences.edit()) {
            putBoolean("IS_LOGGED_IN", isLoggedIn)
            putString("USER_NAME", userName)
            apply()
        }
    }

    // check if user is already logged in using sharedPreferences
    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false)
    }

    // get username from the current session
    fun getLoggedInUsername(): String? {
        return sharedPreferences.getString("USER_NAME", null)
    }
}