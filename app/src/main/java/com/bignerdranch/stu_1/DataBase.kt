package com.bignerdranch.stu_1

import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(
        entities = [User::class],
        version = 2
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }
