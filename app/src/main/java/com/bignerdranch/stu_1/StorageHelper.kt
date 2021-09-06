package com.bignerdranch.stu_1

interface StorageHelper {
    fun updateUser(person: Person)
    fun saveUsers(users:ArrayList<Person>)
    fun getUsers():ArrayList<Person>
    fun deleteUser(person: Person)
}