package com.example.capstone.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.capstone.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT id FROM User WHERE username = :username")
    suspend fun getUserID(username: String): Long
}