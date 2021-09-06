package com.bignerdranch.stu_1

import android.content.Context
import androidx.room.Room

class DataBaseHelper(context:Context): StorageHelper {
    var db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database"
    )
        .allowMainThreadQueries()
        .build()

    override fun getUsers(): ArrayList<Person> {
        return db.userDao().getUsers()?.map { Person.from(it) } as ArrayList<Person>
    }

    override fun saveUsers(users: ArrayList<Person>) {
        db.userDao().deleteUsers()
        db.userDao().saveUsers(users.map { User.from(it) } as ArrayList<User>)
    }

    override fun updateUser(person: Person) {
        db.userDao().saveUser(User.from(person))
    }

    override fun deleteUser(person: Person) {
        db.userDao().deleteUser(User.from(person))
    }
}