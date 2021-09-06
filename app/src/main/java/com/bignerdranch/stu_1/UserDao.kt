package com.bignerdranch.stu_1

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<User>?

//    @Query("UPDATE users SET firstName='' WHERE id = user")
//    fun updateUser(user: User)

    @Query("DELETE FROM users")
    fun deleteUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users: ArrayList<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User)

//    @Query("DELETE FROM users WHERE id=:id")
//    fun deleteUser(id:String)

    @Delete
    fun deleteUser(user: User)
}