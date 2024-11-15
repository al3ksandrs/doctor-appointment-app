package com.example.capstone.data.repository

import android.content.Context
import com.example.capstone.data.database.DACDatabase
import com.example.capstone.data.database.UserDao
import com.example.capstone.data.model.User
import org.mindrot.jbcrypt.BCrypt

class UserRepository(context: Context) {
    private val userDao: UserDao

    // initialize database
    init {
        val database = DACDatabase.getDatabase(context)
        userDao = database!!.userDao()
    }

    // insert user into database
    suspend fun insertUser(user: User) {
        // hash password before inserting it into the database
        val hashedPassword = BCrypt.hashpw(user.password, BCrypt.gensalt())
        userDao.insertUser(user.copy(password = hashedPassword))
    }

    // get user by their username
    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    // validate user password
    suspend fun validateUser(username: String, password: String): User? {
        val user = userDao.getUserByUsername(username)
        return if (user != null && BCrypt.checkpw(password, user.password)) {
            user
        } else {
            null
        }
    }
}