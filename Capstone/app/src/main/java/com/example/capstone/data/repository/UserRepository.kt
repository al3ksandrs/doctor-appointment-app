package com.example.capstone.data.repository

import android.content.Context
import com.example.capstone.data.database.DACDatabase
import com.example.capstone.data.database.UserDao
import com.example.capstone.data.model.User

class UserRepository(context: Context) {
    private val userDao: UserDao

    init {
        val database = DACDatabase.getDatabase(context)
        userDao = database!!.userDao()
    }

    // insert user into database
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    // get user by their username
    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }
}