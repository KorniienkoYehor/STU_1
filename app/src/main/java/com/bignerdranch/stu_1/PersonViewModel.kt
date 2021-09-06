package com.bignerdranch.stu_1

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.R
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PersonViewModel(
    //val sharedPref: SharedPreferences
    val sharedHelper: SharedHelper
) :ViewModel() {


    private var userList=ArrayList<Person>()
    val usersLiveData =  MutableLiveData<ArrayList<Person>>()

    fun getUserList(): ArrayList<Person> = userList

    override fun onCleared() {
        super.onCleared()
        Log.i("PersonViewModel", "PersonViewModel destroyed!")
    }

    fun getPersons() {
        userList=sharedHelper.getUsers()
        usersLiveData.value = userList
    }
    private fun SharedPreferences.Editor.putParcelable(key: String, parcelable: Parcelable) {
        val json = Gson().toJson(parcelable)
        putString(key, json)
    }
    fun addUser(person: Person){
        userList.add(person)
        sharedHelper.saveUsers(userList)
    }
    fun changeUser(person: Person){
        val position: Int=getPosition(person)
        userList.set(position,person)
        sharedHelper.saveUsers(userList)

        //sharedHelper.savePeople(userList)
    }
    fun delete(person: Person){
        val position: Int=getPosition(person)
        userList.removeAt(position)
        sharedHelper.saveUsers(userList)
    }


    fun getPosition(person: Person) : Int{
        for((index, element) in userList.withIndex()){
            if(element.id==person.id){
                return index
            }
        }
        return -1
    }

    companion object{
        const val KEY_USER_LIST = "USER_LIST"
    }
}