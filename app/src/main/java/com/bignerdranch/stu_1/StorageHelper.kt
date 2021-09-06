package com.bignerdranch.stu_1

interface StorageHelper {
    fun saveUsers(users:ArrayList<Person>)
    fun getUsers():ArrayList<Person>
}