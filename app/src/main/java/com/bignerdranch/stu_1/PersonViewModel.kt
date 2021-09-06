package com.bignerdranch.stu_1

import android.content.SharedPreferences
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class PersonViewModel(
    val storageHelper: StorageHelper
) : ViewModel() {


    private var userList = ArrayList<Person>()
    val usersLiveData = MutableLiveData<ArrayList<Person>>()

    fun getUserList(): ArrayList<Person> = userList

    override fun onCleared() {
        super.onCleared()
        Log.i("PersonViewModel", "PersonViewModel destroyed!")
    }

    fun getPersons() {
        userList = storageHelper.getUsers()
        usersLiveData.value = userList
    }

    private fun SharedPreferences.Editor.putParcelable(key: String, parcelable: Parcelable) {
        val json = Gson().toJson(parcelable)
        putString(key, json)
    }

    fun addUser(person: Person) {
        userList.add(person)
        storageHelper.saveUsers(userList)
    }

    fun changeUser(person: Person) {
        val position: Int = getPosition(person)
        userList.set(position, person)
        storageHelper.saveUsers(userList)
    }

    fun delete(person: Person) {
        val position: Int = getPosition(person)
        userList.removeAt(position)
        storageHelper.saveUsers(userList)
    }

    fun getPosition(person: Person): Int {
        for ((index, element) in userList.withIndex()) {
            if (element.id == person.id) {
                return index
            }
        }
        return -1
    }

    companion object {
        const val KEY_USER_LIST = "USER_LIST"
    }
}