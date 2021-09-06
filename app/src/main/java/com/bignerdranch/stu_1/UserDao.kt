package com.bignerdranch.stu_1

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<User>?


    @Query("DELETE FROM users")
    fun deleteUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: ArrayList<User>)
}